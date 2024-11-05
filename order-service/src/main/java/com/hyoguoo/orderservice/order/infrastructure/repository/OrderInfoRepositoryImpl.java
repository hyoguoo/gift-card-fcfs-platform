package com.hyoguoo.orderservice.order.infrastructure.repository;

import com.hyoguoo.orderservice.order.application.port.OrderInfoRepository;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import com.hyoguoo.orderservice.order.infrastructure.entity.OrderInfoEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderInfoRepositoryImpl implements OrderInfoRepository {

    private final JpaOrderInfoRepository jpaOrderInfoRepository;

    @Override
    public Optional<OrderInfo> findById(Long id) {
        return jpaOrderInfoRepository.findById(id).map(OrderInfoEntity::toDomain);
    }

    @Override
    public OrderInfo saveOrUpdate(OrderInfo orderInfo) {
        return jpaOrderInfoRepository.save(OrderInfoEntity.from(orderInfo)).toDomain();
    }
}
