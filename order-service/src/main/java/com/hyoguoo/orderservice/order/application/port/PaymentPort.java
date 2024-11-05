package com.hyoguoo.orderservice.order.application.port;

import com.hyoguoo.orderservice.order.application.dto.command.PaymentCheckoutCommand;

public interface PaymentPort {

    void checkoutPayment(PaymentCheckoutCommand command);
}
