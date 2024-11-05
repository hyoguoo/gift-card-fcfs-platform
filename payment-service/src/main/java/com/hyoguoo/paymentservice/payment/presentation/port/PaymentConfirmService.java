package com.hyoguoo.paymentservice.payment.presentation.port;

import com.hyoguoo.paymentservice.payment.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.application.dto.result.PaymentConfirmResult;

public interface PaymentConfirmService {

    PaymentConfirmResult confirm(PaymentConfirmCommand command);
}
