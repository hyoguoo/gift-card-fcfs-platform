package com.hyoguoo.orderservice.application;

import com.hyoguoo.orderservice.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.orderservice.application.dto.result.CheckoutResult;
import com.hyoguoo.orderservice.application.usecase.OrderInfoCreateUseCase;
import com.hyoguoo.orderservice.application.usecase.OrderedGiftCardLoadUseCase;
import com.hyoguoo.orderservice.application.usecase.PaymentCheckoutUseCase;
import com.hyoguoo.orderservice.domain.OrderInfo;
import com.hyoguoo.orderservice.domain.dto.GiftCardInfo;
import com.hyoguoo.orderservice.presentation.port.OrderCheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderCheckoutServiceImpl implements OrderCheckoutService {

    private final OrderInfoCreateUseCase orderInfoCreateUseCase;
    private final OrderedGiftCardLoadUseCase orderedGiftCardLoadUseCase;
    private final PaymentCheckoutUseCase paymentCheckoutUseCase;

    @Override
    public CheckoutResult checkoutOrder(CheckoutCommand command) {
        GiftCardInfo giftCardInfo = orderedGiftCardLoadUseCase.loadOrderedGiftCard(command.getGiftCardId());
        OrderInfo savedOrderInfo = orderInfoCreateUseCase.createOrderInfo(command, giftCardInfo);

        PaymentCheckoutCommand paymentCheckoutCommand = PaymentCheckoutCommand.builder()
                .buyerId(command.getBuyerId())
                .orderInfoId(savedOrderInfo.getId())
                .orderId(savedOrderInfo.getOrderId())
                .build();

        paymentCheckoutUseCase.checkoutPayment(paymentCheckoutCommand);

        return CheckoutResult.builder()
                .orderId(savedOrderInfo.getOrderId())
                .totalAmount(savedOrderInfo.getPaymentAmount())
                .build();
    }
}
