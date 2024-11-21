package com.hyoguoo.paymentservice.payment.presentation;

import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.application.dto.result.PaymentConfirmResult;
import com.hyoguoo.paymentservice.payment.presentation.dto.request.PaymentConfirmRequest;
import com.hyoguoo.paymentservice.payment.presentation.dto.response.PaymentConfirmResponse;
import com.hyoguoo.paymentservice.payment.presentation.port.PaymentConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentConfirmService paymentConfirmService;

    @PostMapping("/payment/confirm")
    public PaymentConfirmResponse confirmPayment(@RequestBody PaymentConfirmRequest request,
            @RequestHeader("X-USER-ID") String userId) {
        PaymentConfirmCommand command = PaymentConfirmCommand.builder()
                .buyerId(Long.parseLong(userId))
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .paymentKey(request.getPaymentKey())
                .build();
        PaymentConfirmResult result = paymentConfirmService.confirm(command);

        return PaymentConfirmResponse.builder()
                .orderId(result.getOrderId())
                .amount(result.getAmount())
                .build();
    }
}
