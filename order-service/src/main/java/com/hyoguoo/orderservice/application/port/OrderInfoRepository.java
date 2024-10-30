package com.hyoguoo.orderservice.application.port;

import com.hyoguoo.orderservice.domain.OrderInfo;
import java.util.Optional;

public interface OrderInfoRepository {

    Optional<OrderInfo> findById(Long id);

    OrderInfo saveOrUpdate(OrderInfo orderInfo);
}
