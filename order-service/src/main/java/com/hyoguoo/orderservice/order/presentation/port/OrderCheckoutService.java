package com.hyoguoo.orderservice.order.presentation.port;

import com.hyoguoo.orderservice.order.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.order.application.dto.result.CheckoutResult;

public interface OrderCheckoutService {

    CheckoutResult checkoutOrder(CheckoutCommand command);
}
