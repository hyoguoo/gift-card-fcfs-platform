package com.hyoguoo.paymentservice.payment.domain;


import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.domain.dto.TossPaymentInfo;
import com.hyoguoo.paymentservice.payment.domain.dto.enums.TossPaymentStatus;
import com.hyoguoo.paymentservice.payment.domain.enums.PaymentEventStatus;
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
            throw new IllegalArgumentException("Invalid status to execute");
        }
        this.paymentKey = paymentKey;
        this.status = PaymentEventStatus.IN_PROGRESS;
        this.executedAt = executedAt;
    }

    public void validateCompletionStatus(PaymentConfirmCommand paymentConfirmCommand, TossPaymentInfo paymentInfo) {
        if (!this.buyerId.equals(paymentConfirmCommand.getBuyerId())) {
            throw new IllegalArgumentException("Invalid buyerId");
        }

        if (!paymentConfirmCommand.getPaymentKey().equals(paymentInfo.getPaymentKey()) ||
                !paymentConfirmCommand.getPaymentKey().equals(this.paymentKey)) {
            throw new IllegalArgumentException("Invalid paymentKey");
        }

        if (!paymentConfirmCommand.getAmount().equals(this.totalAmount)) {
            throw new IllegalArgumentException("Invalid amount");
        }

        if (!this.orderId.equals(paymentConfirmCommand.getOrderId())) {
            throw new IllegalArgumentException("Invalid orderId");
        }

        if (paymentInfo.getPaymentDetails().getStatus() != TossPaymentStatus.IN_PROGRESS &&
                paymentInfo.getPaymentDetails().getStatus() != TossPaymentStatus.DONE) {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }

    public void done(LocalDateTime approvedAt) {
        if (this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.DONE &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw new IllegalArgumentException("Invalid status to done");
        }
        this.approvedAt = approvedAt;
        this.status = PaymentEventStatus.DONE;
    }

    public void fail() {
        if (this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw new IllegalArgumentException("Invalid status to fail");
        }
        this.status = PaymentEventStatus.FAILED;
    }

    public void unknown() {
        if (this.status != PaymentEventStatus.READY &&
                this.status != PaymentEventStatus.IN_PROGRESS &&
                this.status != PaymentEventStatus.UNKNOWN) {
            throw new IllegalArgumentException("Invalid status to unknown");
        }
        this.status = PaymentEventStatus.UNKNOWN;
    }
}
