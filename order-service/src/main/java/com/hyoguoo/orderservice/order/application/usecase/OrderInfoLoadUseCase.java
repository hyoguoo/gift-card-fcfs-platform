package com.hyoguoo.orderservice.order.application.usecase;

import com.hyoguoo.orderservice.order.application.port.OrderInfoRepository;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import com.hyoguoo.orderservice.order.exception.OrderFoundException;
import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInfoLoadUseCase {

    private final OrderInfoRepository orderInfoRepository;

    public OrderInfo loadOrderInfo(Long orderInfoId) {
        return orderInfoRepository.findById(orderInfoId)
                .orElseThrow(() -> OrderFoundException.of(OrderErrorCode.ORDER_NOT_FOUND));
    }
}
