package com.hyoguoo.paymentservice.application.port;

import com.hyoguoo.paymentservice.domain.PaymentEvent;
import java.util.Optional;

public interface PaymentEventRepository {

    Optional<PaymentEvent> findById(Long id);

    Optional<PaymentEvent> findByOrderId(String orderId);

    PaymentEvent saveOrUpdate(PaymentEvent paymentEvent);
}
