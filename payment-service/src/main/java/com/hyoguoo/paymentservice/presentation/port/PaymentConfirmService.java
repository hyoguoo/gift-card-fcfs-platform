package com.hyoguoo.paymentservice.presentation.port;

import com.hyoguoo.paymentservice.application.dto.command.PaymentConfirmCommand;
import com.hyoguoo.paymentservice.application.dto.result.PaymentConfirmResult;

public interface PaymentConfirmService {

    PaymentConfirmResult confirm(PaymentConfirmCommand command);
}
