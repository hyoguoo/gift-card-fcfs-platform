package com.hyoguoo.paymentservice.infrastructure.repository;

import com.hyoguoo.paymentservice.application.port.PaymentEventRepository;
import com.hyoguoo.paymentservice.domain.PaymentEvent;
import com.hyoguoo.util.ReflectionUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FakePaymentEventRepositoryImpl implements PaymentEventRepository {

    private final Map<Long, PaymentEvent> paymentEventDatabase = new HashMap<>();
    private Long id = 0L;

    @Override
    public Optional<PaymentEvent> findById(Long id) {
        return Optional.ofNullable(paymentEventDatabase.get(id));
    }

    @Override
    public Optional<PaymentEvent> findByOrderId(String orderId) {
        return paymentEventDatabase.values().stream()
                .filter(event -> event.getOrderId().equals(orderId))
                .findFirst();
    }

    @Override
    public PaymentEvent saveOrUpdate(PaymentEvent paymentEvent) {
        if (paymentEvent.getId() == null) {
            ReflectionUtil.setField(paymentEvent, "id", ++id);
        }

        return paymentEventDatabase.put(paymentEvent.getId(), paymentEvent);
    }
}
