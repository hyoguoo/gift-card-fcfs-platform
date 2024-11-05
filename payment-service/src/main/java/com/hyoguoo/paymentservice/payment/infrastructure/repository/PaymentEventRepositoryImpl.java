package com.hyoguoo.paymentservice.payment.infrastructure.repository;

import com.hyoguoo.paymentservice.payment.application.port.PaymentEventRepository;
import com.hyoguoo.paymentservice.payment.domain.PaymentEvent;
import com.hyoguoo.paymentservice.payment.infrastructure.entity.PaymentEventEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentEventRepositoryImpl implements PaymentEventRepository {

    private final JpaPaymentEventRepository jpaPaymentEventRepository;

    @Override
    public Optional<PaymentEvent> findById(Long id) {
        return jpaPaymentEventRepository.findById(id).map(PaymentEventEntity::toDomain);
    }

    @Override
    public Optional<PaymentEvent> findByOrderId(String orderId) {
        return jpaPaymentEventRepository.findByOrderId(orderId).map(PaymentEventEntity::toDomain);
    }

    @Override
    public PaymentEvent saveOrUpdate(PaymentEvent paymentEvent) {
        return jpaPaymentEventRepository.save(PaymentEventEntity.from(paymentEvent)).toDomain();
    }
}
