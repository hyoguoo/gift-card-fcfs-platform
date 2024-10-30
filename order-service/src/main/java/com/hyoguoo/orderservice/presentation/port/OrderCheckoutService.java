package com.hyoguoo.orderservice.presentation.port;

import com.hyoguoo.orderservice.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.application.dto.result.CheckoutResult;

public interface OrderCheckoutService {

    CheckoutResult checkoutOrder(CheckoutCommand command);
}
