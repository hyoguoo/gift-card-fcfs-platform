package com.hyoguoo.paymentservice.payment.application.port;

import com.hyoguoo.paymentservice.payment.domain.PaymentEvent;
import java.util.Optional;

public interface PaymentEventRepository {

    Optional<PaymentEvent> findById(Long id);

    Optional<PaymentEvent> findByOrderId(String orderId);

    PaymentEvent saveOrUpdate(PaymentEvent paymentEvent);
}
