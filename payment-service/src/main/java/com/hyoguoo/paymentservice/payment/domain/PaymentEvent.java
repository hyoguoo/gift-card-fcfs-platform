package com.hyoguoo.paymentservice.payment.domain;


import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.domain.dto.TossPaymentInfo;
import com.hyoguoo.paymentservice.payment.domain.dto.enums.TossPaymentStatus;
import com.hyoguoo.paymentservice.payment.domain.enums.PaymentEventStatus;
import com.hyoguoo.paymentservice.payment.exception.PaymentConfirmationException;
import com.hyoguoo.paymentservice.payment.exception.PaymentDoneValidateException;
import com.hyoguoo.paymentservice.payment.exception.PaymentExecuteValidateException;
import com.hyoguoo.paymentservice.payment.exception.PaymentFailValidateException;
import com.hyoguoo.paymentservice.payment.exception.PaymentUnknownValidateException;
import com.hyoguoo.paymentservice.payment.exception.common.PaymentErrorCode;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(builderMethodName = "allArgsBuilder", buildMethodName = "allArgsBuild")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentEvent {

    private Long id;
    private Long buyerId;
    private Long orderInfoId;
    private Long orderedGiftCardId;
    private String orderId;
    private String paymentKey;
    private Long totalAmount;
    private PaymentEventStatus status;
    private LocalDateTime executedAt;
    private LocalDateTime approvedAt;

    @Builder(builderMethodName = "requiredBuilder", buildMethodName = "requiredBuild")
    @SuppressWarnings("unused")
    protected PaymentEvent(PaymentCheckoutCommand command) {
        this.buyerId = command.getBuyerId();
        this.orderInfoId = command.getOrderInfoId();
        this.orderedGiftCardId = command.getGiftCardId();
        this.orderId = command.getOrderId();
        this.totalAmount = command.getGiftCardPrice();

        this.status = PaymentEventStatus.READY;
    }

    public void execute(String paymentKey, LocalDateTime executedAt) {
        if (this.status != PaymentEventStatus.READY &&
                this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw PaymentExecuteValidateException.of(PaymentErrorCode.INVALID_EXECUTION_STATUS);
        }
        this.paymentKey = paymentKey;
        this.status = PaymentEventStatus.IN_PROGRESS;
        this.executedAt = executedAt;
    }

    public void validateConfirmation(PaymentConfirmCommand paymentConfirmCommand, TossPaymentInfo paymentInfo)
            throws PaymentConfirmationException {
        if (!this.buyerId.equals(paymentConfirmCommand.getBuyerId())) {
            throw PaymentConfirmationException.of(PaymentErrorCode.BUYER_ID_NOT_MATCH);
        }

        if (!paymentConfirmCommand.getPaymentKey().equals(paymentInfo.getPaymentKey()) ||
                !paymentConfirmCommand.getPaymentKey().equals(this.paymentKey)) {
            throw PaymentConfirmationException.of(PaymentErrorCode.PAYMENT_KEY_NOT_MATCH);
        }

        if (!paymentConfirmCommand.getAmount().equals(this.totalAmount)) {
            throw PaymentConfirmationException.of(PaymentErrorCode.INVALID_TOTAL_AMOUNT);
        }

        if (!this.orderId.equals(paymentConfirmCommand.getOrderId())) {
            throw PaymentConfirmationException.of(PaymentErrorCode.INVALID_ORDER_ID);
        }

        if (paymentInfo.getPaymentDetails().getStatus() != TossPaymentStatus.IN_PROGRESS &&
                paymentInfo.getPaymentDetails().getStatus() != TossPaymentStatus.DONE) {
            throw PaymentConfirmationException.of(PaymentErrorCode.INVALID_STATUS_TO_EXECUTE);
        }
    }

    public void done(LocalDateTime approvedAt) throws PaymentDoneValidateException {
        if (this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.DONE &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw PaymentDoneValidateException.of(PaymentErrorCode.INVALID_STATUS_TO_DONE);
        }
        this.approvedAt = approvedAt;
        this.status = PaymentEventStatus.DONE;
    }

    public void fail() {
        if (this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw PaymentFailValidateException.of(PaymentErrorCode.INVALID_STATUS_TO_FAIL);
        }
        this.status = PaymentEventStatus.FAILED;
    }

    public void unknown() {
        if (this.status != PaymentEventStatus.READY &&
                this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw PaymentUnknownValidateException.of(PaymentErrorCode.INVALID_STATUS_TO_UNKNOWN);
        }
        this.status = PaymentEventStatus.UNKNOWN;
    }
}
