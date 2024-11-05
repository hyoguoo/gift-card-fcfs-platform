package com.hyoguoo.paymentservice.payment.application.usecase;

import com.hyoguoo.paymentservice.core.common.application.port.LocalDateTimeProvider;
import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.application.dto.command.TossPaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.application.port.OrderInfoMessageProducer;
import com.hyoguoo.paymentservice.payment.application.port.PaymentEventRepository;
import com.hyoguoo.paymentservice.payment.application.port.TossPaymentGateway;
import com.hyoguoo.paymentservice.payment.domain.PaymentEvent;
import com.hyoguoo.paymentservice.payment.domain.dto.TossPaymentInfo;
import com.hyoguoo.paymentservice.payment.domain.dto.enums.PaymentConfirmResultStatus;
import com.hyoguoo.paymentservice.payment.exception.PaymentTossNonRetryableException;
import com.hyoguoo.paymentservice.payment.exception.PaymentTossRetryableException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProcessorUseCase {

    private final PaymentEventRepository paymentEventRepository;
    private final LocalDateTimeProvider localDateTimeProvider;
    private final TossPaymentGateway tossPaymentGateway;
    private final OrderInfoMessageProducer orderInfoMessageProducer;

    public PaymentEvent executePayment(PaymentEvent paymentEvent, String paymentKey) {
        paymentEvent.execute(paymentKey, localDateTimeProvider.now());
        return paymentEventRepository.saveOrUpdate(paymentEvent);
    }

    public void validateCompletionStatus(
            PaymentEvent paymentEvent,
            PaymentConfirmCommand paymentConfirmCommand
    ) {
        TossPaymentInfo tossPaymentInfo = tossPaymentGateway.getPaymentInfoByOrderId(
                paymentConfirmCommand.getOrderId()
        );

        paymentEvent.validateCompletionStatus(paymentConfirmCommand, tossPaymentInfo);
    }

    public TossPaymentInfo confirmPaymentWithGateway(PaymentConfirmCommand paymentConfirmCommand)
            throws PaymentTossRetryableException, PaymentTossNonRetryableException {
        TossPaymentConfirmCommand tossConfirmGatewayCommand = TossPaymentConfirmCommand.builder()
                .orderId(paymentConfirmCommand.getOrderId())
                .paymentKey(paymentConfirmCommand.getPaymentKey())
                .amount(paymentConfirmCommand.getAmount())
                .idempotencyKey(paymentConfirmCommand.getOrderId())
                .build();

        TossPaymentInfo tossPaymentInfo = tossPaymentGateway.confirmPayment(tossConfirmGatewayCommand,
                paymentConfirmCommand.getOrderId());

        PaymentConfirmResultStatus paymentConfirmResultStatus = tossPaymentInfo.getPaymentConfirmResultStatus();

        return switch (paymentConfirmResultStatus) {
            case PaymentConfirmResultStatus.SUCCESS -> tossPaymentInfo;
            case PaymentConfirmResultStatus.RETRYABLE_FAILURE -> throw new PaymentTossRetryableException();
            case PaymentConfirmResultStatus.NON_RETRYABLE_FAILURE -> throw new PaymentTossNonRetryableException();
        };
    }

    public PaymentEvent markPaymentAsDone(PaymentEvent paymentEvent, LocalDateTime approvedAt) {
        paymentEvent.done(approvedAt);
        PaymentEvent savedPaymentEvent = paymentEventRepository.saveOrUpdate(paymentEvent);
        orderInfoMessageProducer.sendOrderCompleted(savedPaymentEvent.getOrderInfoId());

        return savedPaymentEvent;
    }

    public PaymentEvent markPaymentAsFail(PaymentEvent paymentEvent) {
        paymentEvent.fail();
        PaymentEvent savedPaymentEvent = paymentEventRepository.saveOrUpdate(paymentEvent);
        orderInfoMessageProducer.sendOrderFailed(savedPaymentEvent.getOrderInfoId());

        return savedPaymentEvent;
    }

    public PaymentEvent markPaymentAsUnknown(PaymentEvent paymentEvent) {
        paymentEvent.unknown();
        return paymentEventRepository.saveOrUpdate(paymentEvent);
    }
}
