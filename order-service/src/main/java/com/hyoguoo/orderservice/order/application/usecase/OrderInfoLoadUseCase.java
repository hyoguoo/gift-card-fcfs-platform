package com.hyoguoo.orderservice.order.application.usecase;

import com.hyoguoo.orderservice.order.application.port.OrderInfoRepository;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInfoLoadUseCase {

    private final OrderInfoRepository orderInfoRepository;

    public OrderInfo loadOrderInfo(Long orderInfoId) {
        return orderInfoRepository.findById(orderInfoId)
                .orElseThrow(() -> new IllegalArgumentException("OrderInfo not found"));
    }
}
