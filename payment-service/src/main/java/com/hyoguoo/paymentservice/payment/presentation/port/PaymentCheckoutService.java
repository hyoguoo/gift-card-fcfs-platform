package com.hyoguoo.paymentservice.payment.presentation.port;

import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentCheckoutCommand;

public interface PaymentCheckoutService {

    void checkoutPayment(PaymentCheckoutCommand command);
}
