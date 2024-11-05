package com.hyoguoo.paymentservice.payment.presentation;

import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.paymentservice.payment.presentation.dto.request.PaymentCheckoutRequest;
import com.hyoguoo.paymentservice.payment.presentation.port.PaymentCheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InternalPaymentController {

    private final PaymentCheckoutService paymentCheckoutService;

    @PostMapping("/internal/payment/checkout")
    public void checkoutPayment(@RequestBody PaymentCheckoutRequest request) {
        PaymentCheckoutCommand command = PaymentCheckoutCommand.builder()
                .buyerId(request.getBuyerId())
                .orderInfoId(request.getOrderInfoId())
                .orderId(request.getOrderId())
                .giftCardId(request.getGiftCardId())
                .giftCardPrice(request.getGiftCardPrice())
                .build();

        paymentCheckoutService.checkoutPayment(command);
    }
}
