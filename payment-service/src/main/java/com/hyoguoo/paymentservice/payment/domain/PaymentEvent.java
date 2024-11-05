package com.hyoguoo.paymentservice.payment.domain;


import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.domain.dto.TossPaymentInfo;
import com.hyoguoo.paymentservice.payment.domain.dto.enums.TossPaymentStatus;
import com.hyoguoo.paymentservice.payment.domain.enums.PaymentEventStatus;
import com.hyoguoo.paymentservice.payment.exception.PaymentValidateException;
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
            throw new PaymentValidateException();
        }
        this.paymentKey = paymentKey;
        this.status = PaymentEventStatus.IN_PROGRESS;
        this.executedAt = executedAt;
    }

    public void validateCompletionStatus(PaymentConfirmCommand paymentConfirmCommand, TossPaymentInfo paymentInfo) {
        if (!this.buyerId.equals(paymentConfirmCommand.getBuyerId())) {
            throw new PaymentValidateException();
        }

        if (!paymentConfirmCommand.getPaymentKey().equals(paymentInfo.getPaymentKey()) ||
                !paymentConfirmCommand.getPaymentKey().equals(this.paymentKey)) {
            throw new PaymentValidateException();
        }

        if (!paymentConfirmCommand.getAmount().equals(this.totalAmount)) {
            throw new PaymentValidateException();
        }

        if (!this.orderId.equals(paymentConfirmCommand.getOrderId())) {
            throw new PaymentValidateException();
        }

        if (paymentInfo.getPaymentDetails().getStatus() != TossPaymentStatus.IN_PROGRESS &&
                paymentInfo.getPaymentDetails().getStatus() != TossPaymentStatus.DONE) {
            throw new PaymentValidateException();
        }
    }

    public void done(LocalDateTime approvedAt) {
        if (this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.DONE &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw new PaymentValidateException();
        }
        this.approvedAt = approvedAt;
        this.status = PaymentEventStatus.DONE;
    }

    public void fail() {
        if (this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw new PaymentValidateException();
        }
        this.status = PaymentEventStatus.FAILED;
    }

    public void unknown() {
        if (this.status != PaymentEventStatus.READY &&
                this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw new PaymentValidateException();
        }
        this.status = PaymentEventStatus.UNKNOWN;
    }
}
