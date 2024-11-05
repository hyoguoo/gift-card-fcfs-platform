package com.hyoguoo.orderservice.order.application.port;

import com.hyoguoo.orderservice.order.domain.OrderInfo;
import java.util.Optional;

public interface OrderInfoRepository {

    Optional<OrderInfo> findById(Long id);

    OrderInfo saveOrUpdate(OrderInfo orderInfo);
}
