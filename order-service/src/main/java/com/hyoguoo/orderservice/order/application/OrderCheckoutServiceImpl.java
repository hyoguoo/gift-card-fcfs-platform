package com.hyoguoo.orderservice.order.application;

import com.hyoguoo.orderservice.order.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.order.application.dto.command.PaymentCheckoutCommand;
import com.hyoguoo.orderservice.order.application.dto.result.CheckoutResult;
import com.hyoguoo.orderservice.order.application.usecase.OrderInfoCreateUseCase;
import com.hyoguoo.orderservice.order.application.usecase.OrderInfoProcessUseCase;
import com.hyoguoo.orderservice.order.application.usecase.OrderedGiftCardLoadUseCase;
import com.hyoguoo.orderservice.order.application.usecase.PaymentCheckoutUseCase;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import com.hyoguoo.orderservice.order.domain.dto.GiftCardInfo;
import com.hyoguoo.orderservice.order.presentation.port.OrderCheckoutService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCheckoutServiceImpl implements OrderCheckoutService {

    private final OrderInfoCreateUseCase orderInfoCreateUseCase;
    private final OrderedGiftCardLoadUseCase orderedGiftCardLoadUseCase;
    private final PaymentCheckoutUseCase paymentCheckoutUseCase;
    private final OrderInfoProcessUseCase orderInfoProcessUseCase;

    @Override
    public CheckoutResult checkoutOrder(CheckoutCommand command) {
        GiftCardInfo giftCardInfo = orderedGiftCardLoadUseCase.loadOrderedGiftCard(command.getGiftCardId());
        OrderInfo savedOrderInfo = orderInfoCreateUseCase.createOrderInfo(command, giftCardInfo);

        try {
            checkoutPayment(command, savedOrderInfo, giftCardInfo);
        } catch (Exception e) {
            handleUnknownException(savedOrderInfo);
            throw e;
        }

        return CheckoutResult.builder()
                .orderId(savedOrderInfo.getOrderId())
                .totalAmount(savedOrderInfo.getPaymentAmount())
                .build();
    }

    private void checkoutPayment(CheckoutCommand command, OrderInfo savedOrderInfo, GiftCardInfo giftCardInfo) {
        PaymentCheckoutCommand paymentCheckoutCommand = PaymentCheckoutCommand.builder()
                .buyerId(command.getBuyerId())
                .orderInfoId(savedOrderInfo.getId())
                .orderId(savedOrderInfo.getOrderId())
                .giftCardId(command.getGiftCardId())
                .giftCardPrice(giftCardInfo.getPrice())
                .build();

        paymentCheckoutUseCase.checkoutPayment(paymentCheckoutCommand);
    }

    private void handleUnknownException(OrderInfo savedOrderInfo) {
        log.error("Failed to checkout payment. orderId={}", savedOrderInfo.getOrderId());
        orderInfoProcessUseCase.failedOrderInfo(savedOrderInfo);
    }
}
