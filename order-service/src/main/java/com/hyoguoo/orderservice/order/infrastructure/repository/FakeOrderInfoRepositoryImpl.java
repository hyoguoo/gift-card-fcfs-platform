package com.hyoguoo.orderservice.order.infrastructure.repository;

import com.hyoguoo.orderservice.order.application.port.OrderInfoRepository;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import com.hyoguoo.util.ReflectionUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FakeOrderInfoRepositoryImpl implements OrderInfoRepository {

    private final Map<Long, OrderInfo> orderInfoMa = new HashMap<>();
    private Long id = 0L;

    @Override
    public Optional<OrderInfo> findById(Long id) {
        return Optional.ofNullable(orderInfoMa.get(id));
    }

    @Override
    public OrderInfo saveOrUpdate(OrderInfo orderInfo) {
        if (orderInfo.getId() == null) {
            ReflectionUtil.setField(orderInfo, "id", ++id);
        }
        return orderInfoMa.put(orderInfo.getId(), orderInfo);
    }
}
