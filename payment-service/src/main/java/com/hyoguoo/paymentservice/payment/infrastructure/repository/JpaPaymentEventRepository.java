package com.hyoguoo.paymentservice.payment.infrastructure.repository;

import com.hyoguoo.paymentservice.payment.infrastructure.entity.PaymentEventEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaPaymentEventRepository extends JpaRepository<PaymentEventEntity, Long> {

    @Query("""
            SELECT pe
            FROM PaymentEventEntity pe
            WHERE pe.orderId = :orderId
            """)
    Optional<PaymentEventEntity> findByOrderId(String orderId);
}
