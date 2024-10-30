package com.hyoguoo.orderservice.application.port;

import com.hyoguoo.orderservice.application.dto.command.PaymentCheckoutCommand;

public interface PaymentPort {

    void checkoutPayment(PaymentCheckoutCommand command);
}
