package com.hyoguoo.orderservice.order.infrastructure.repository;

import com.hyoguoo.orderservice.order.infrastructure.entity.OrderInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderInfoRepository extends JpaRepository<OrderInfoEntity, Long> {

}
