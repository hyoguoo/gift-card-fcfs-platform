package com.hyoguoo.paymentservice.payment.application;

import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.application.dto.result.PaymentConfirmResult;
import com.hyoguoo.paymentservice.payment.application.usecase.OrderedGiftCardStockUseCase;
import com.hyoguoo.paymentservice.payment.application.usecase.PaymentLoadUseCase;
import com.hyoguoo.paymentservice.payment.application.usecase.PaymentProcessorUseCase;
import com.hyoguoo.paymentservice.payment.domain.PaymentEvent;
import com.hyoguoo.paymentservice.payment.domain.dto.TossPaymentInfo;
import com.hyoguoo.paymentservice.payment.exception.PaymentConfirmException;
import com.hyoguoo.paymentservice.payment.exception.PaymentConfirmationException;
import com.hyoguoo.paymentservice.payment.exception.PaymentDoneValidateException;
import com.hyoguoo.paymentservice.payment.exception.PaymentOrderedStockException;
import com.hyoguoo.paymentservice.payment.exception.PaymentTossNonRetryableException;
import com.hyoguoo.paymentservice.payment.exception.PaymentTossRetryableException;
import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import com.hyoguoo.paymentservice.payment.presentation.port.PaymentConfirmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Primary
@RequiredArgsConstructor
public class PaymentConfirmWithLuaScriptServiceImpl implements PaymentConfirmService {

    private final PaymentLoadUseCase paymentLoadUseCase;
    private final PaymentProcessorUseCase paymentProcessorUseCase;
    private final OrderedGiftCardStockUseCase orderedGiftCardStockUseCase;

    @Override
    public PaymentConfirmResult confirm(PaymentConfirmCommand command) {
        PaymentEvent paymentEvent = paymentLoadUseCase.loadPayment(command.getOrderId());
        paymentProcessorUseCase.executePayment(paymentEvent, command.getPaymentKey());

        validateAndDecreaseStock(paymentEvent);

        return processPayment(command, paymentEvent);
    }

    private void validateAndDecreaseStock(PaymentEvent paymentEvent) {
        try {
            orderedGiftCardStockUseCase.decreaseStockForOrders(paymentEvent.getOrderedGiftCardId(), 1);
        } catch (PaymentOrderedStockException e) {
            handleStockFailure(paymentEvent);
            throw PaymentConfirmException.of(PaymentErrorCode.ORDERED_GIFT_CARD_STOCK_NOT_ENOUGH);
        }
    }

    private PaymentConfirmResult processPayment(PaymentConfirmCommand command, PaymentEvent paymentEvent) {
        try {
            PaymentEvent completedPayment = processPayment(paymentEvent, command);

            return PaymentConfirmResult.builder()
                    .amount(completedPayment.getTotalAmount())
                    .orderId(completedPayment.getOrderId())
                    .build();
        } catch (PaymentTossRetryableException e) {
            handleRetryableFailure(paymentEvent);
            throw PaymentConfirmException.of(PaymentErrorCode.TOSS_RETRYABLE_ERROR);
        } catch (PaymentTossNonRetryableException e) {
            handleNonRetryableFailure(paymentEvent);
            throw PaymentConfirmException.of(PaymentErrorCode.TOSS_NON_RETRYABLE_ERROR);
        } catch (PaymentConfirmationException e) {
            handleConfirmationFailure(paymentEvent);
            throw PaymentConfirmException.of(PaymentErrorCode.INVALID_STATUS_TO_CONFIRM);
        } catch (PaymentDoneValidateException e) {
            handleDoneValidationFailure(paymentEvent);
            throw PaymentConfirmException.of(PaymentErrorCode.INVALID_STATUS_TO_DONE);
        } catch (Exception e) {
            handleUnknownException(paymentEvent);
            throw e;
        }
    }

    private PaymentEvent processPayment(PaymentEvent paymentEvent, PaymentConfirmCommand command)
            throws PaymentTossNonRetryableException, PaymentTossRetryableException, PaymentConfirmationException, PaymentDoneValidateException {
        paymentProcessorUseCase.validateCompletionStatus(paymentEvent, command);

        TossPaymentInfo tossPaymentInfo = paymentProcessorUseCase.confirmPaymentWithGateway(command);

        return paymentProcessorUseCase.markPaymentAsDone(
                paymentEvent,
                tossPaymentInfo.getPaymentDetails().getApprovedAt()
        );
    }

    private void handleStockFailure(PaymentEvent paymentEvent) {
        log.info("Ordered gift card stock is not enough for Order ID: {}, GiftCard ID: {}",
                paymentEvent.getOrderId(), paymentEvent.getOrderedGiftCardId());
        paymentProcessorUseCase.markPaymentAsFail(paymentEvent);
    }

    private void handleNonRetryableFailure(PaymentEvent paymentEvent) {
        log.info("Non-retryable failure occurred for Order ID: {}, GiftCard ID: {}",
                paymentEvent.getOrderId(), paymentEvent.getOrderedGiftCardId());
        orderedGiftCardStockUseCase.increaseStockForOrders(paymentEvent.getOrderedGiftCardId(), 1);
        paymentProcessorUseCase.markPaymentAsFail(paymentEvent);
    }

    private void handleRetryableFailure(PaymentEvent paymentEvent) {
        log.info("Retryable failure detected for Order ID: {}, retrying payment confirmation",
                paymentEvent.getOrderId());
        paymentProcessorUseCase.markPaymentAsUnknown(paymentEvent);
    }

    private void handleConfirmationFailure(PaymentEvent paymentEvent) {
        log.info(
                "Payment confirmation validation failed for Order ID: {}, reverting stock and marking payment as failed",
                paymentEvent.getOrderId());
        orderedGiftCardStockUseCase.increaseStockForOrders(paymentEvent.getOrderedGiftCardId(), 1);
        paymentProcessorUseCase.markPaymentAsFail(paymentEvent);
    }

    private void handleUnknownException(PaymentEvent paymentEvent) {
        log.error("Unknown exception occurred during processing for Order ID: {}, GiftCard ID: {}",
                paymentEvent.getOrderId(), paymentEvent.getOrderedGiftCardId());
        orderedGiftCardStockUseCase.increaseStockForOrders(paymentEvent.getOrderedGiftCardId(), 1);
        paymentProcessorUseCase.markPaymentAsFail(paymentEvent);
    }

    private void handleDoneValidationFailure(PaymentEvent paymentEvent) {
        log.error("Validation failure after payment completion for Order ID: {}",
                paymentEvent.getOrderId());
    }
}
