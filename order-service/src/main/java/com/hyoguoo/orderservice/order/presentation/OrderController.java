package com.hyoguoo.orderservice.order.presentation;

import com.hyoguoo.orderservice.order.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.order.application.dto.result.CheckoutResult;
import com.hyoguoo.orderservice.order.presentation.dto.request.CheckoutRequest;
import com.hyoguoo.orderservice.order.presentation.dto.response.CheckoutResponse;
import com.hyoguoo.orderservice.order.presentation.port.OrderCheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderCheckoutService orderCheckoutService;

    @PostMapping("/order/checkout")
    public CheckoutResponse checkoutOrder(@RequestBody CheckoutRequest request,
            @RequestHeader("X-USER-ID") String userId) {
        CheckoutCommand command = CheckoutCommand.builder()
                .buyerId(Long.parseLong(userId))
                .giftCardId(request.getGiftCardId())
                .build();

        CheckoutResult result = orderCheckoutService.checkoutOrder(command);

        return CheckoutResponse.builder()
                .orderId(result.getOrderId())
                .totalAmount(result.getTotalAmount())
                .build();
    }
}
