package com.hyoguoo.paymentservice.application;

import com.hyoguoo.paymentservice.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.application.dto.result.PaymentConfirmResult;
import com.hyoguoo.paymentservice.application.port.OrderInfoMessageProducer;
import com.hyoguoo.paymentservice.application.usecase.OrderedGiftCardStockUseCase;
import com.hyoguoo.paymentservice.application.usecase.PaymentLoadUseCase;
import com.hyoguoo.paymentservice.application.usecase.PaymentProcessorUseCase;
import com.hyoguoo.paymentservice.domain.PaymentEvent;
import com.hyoguoo.paymentservice.domain.dto.TossPaymentInfo;
import com.hyoguoo.paymentservice.exception.PaymentOrderedStockException;
import com.hyoguoo.paymentservice.exception.PaymentTossNonRetryableException;
import com.hyoguoo.paymentservice.exception.PaymentTossRetryableException;
import com.hyoguoo.paymentservice.presentation.port.PaymentConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentConfirmServiceImpl implements PaymentConfirmService {

    private final OrderInfoMessageProducer orderInfoMessageProducer;
    private final PaymentLoadUseCase paymentLoadUseCase;
    private final PaymentProcessorUseCase paymentProcessorUseCase;
    private final OrderedGiftCardStockUseCase orderedGiftCardStockUseCase;

    @Override
    public PaymentConfirmResult confirm(PaymentConfirmCommand command) {
        PaymentEvent paymentEvent = paymentLoadUseCase.loadPayment(command.getOrderId());
        paymentProcessorUseCase.executePayment(paymentEvent, command.getPaymentKey());

        try {
            orderedGiftCardStockUseCase.decreaseStockForOrders(paymentEvent.getOrderedGiftCardId(), 1);
        } catch (PaymentOrderedStockException e) {
            handleStockFailure(paymentEvent);
            throw new IllegalArgumentException("Ordered gift card stock not enough");
        }

        try {
            PaymentEvent completedPayment = processPayment(paymentEvent, command);

            orderInfoMessageProducer.sendOrderCompleted(paymentEvent.getOrderInfoId());

            return PaymentConfirmResult.builder()
                    .amount(completedPayment.getTotalAmount())
                    .orderId(completedPayment.getOrderId())
                    .build();
        } catch (PaymentTossRetryableException e) {
            handleRetryableFailure(paymentEvent);
            throw new IllegalArgumentException("Toss retryable error");
        } catch (PaymentTossNonRetryableException e) {
            handleNonRetryableFailure(paymentEvent);
            throw new IllegalArgumentException("Toss non-retryable error");
        } catch (Exception e) {
            handleUnknownException(paymentEvent);
            throw new IllegalArgumentException("Unknown error");
        }
    }

    private PaymentEvent processPayment(PaymentEvent paymentEvent, PaymentConfirmCommand command)
            throws PaymentTossNonRetryableException, PaymentTossRetryableException {
        paymentProcessorUseCase.validateCompletionStatus(paymentEvent, command);

        TossPaymentInfo tossPaymentInfo = paymentProcessorUseCase.confirmPaymentWithGateway(command);

        return paymentProcessorUseCase.markPaymentAsDone(
                paymentEvent,
                tossPaymentInfo.getPaymentDetails().getApprovedAt()
        );
    }

    private void handleStockFailure(PaymentEvent paymentEvent) {
        markAsFailAndNotifyFailure(paymentEvent);
    }

    private void handleNonRetryableFailure(PaymentEvent paymentEvent) {
        orderedGiftCardStockUseCase.increaseStockForOrders(paymentEvent.getOrderedGiftCardId(), 1);
        markAsFailAndNotifyFailure(paymentEvent);
    }

    private void handleRetryableFailure(PaymentEvent paymentEvent) {
        paymentProcessorUseCase.markPaymentAsUnknown(paymentEvent);
    }

    private void handleUnknownException(PaymentEvent paymentEvent) {
        orderedGiftCardStockUseCase.increaseStockForOrders(paymentEvent.getOrderedGiftCardId(), 1);
        markAsFailAndNotifyFailure(paymentEvent);
    }

    private void markAsFailAndNotifyFailure(PaymentEvent paymentEvent) {
        PaymentEvent failedPaymentEvent = paymentProcessorUseCase.markPaymentAsFail(paymentEvent);
        orderInfoMessageProducer.sendOrderFailed(failedPaymentEvent.getOrderInfoId());
    }
}
