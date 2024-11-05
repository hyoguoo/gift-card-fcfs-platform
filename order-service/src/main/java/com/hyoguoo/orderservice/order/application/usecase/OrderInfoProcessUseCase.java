package com.hyoguoo.orderservice.order.application.usecase;

import com.hyoguoo.orderservice.order.application.port.OrderInfoRepository;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInfoProcessUseCase {

    private final OrderInfoRepository orderInfoRepository;

    public void completedOrderInfo(OrderInfo orderInfo) {
        orderInfo.completeOrder();
        orderInfoRepository.saveOrUpdate(orderInfo);
    }

    public void failedOrderInfo(OrderInfo orderInfo) {
        orderInfo.failOrder();
        orderInfoRepository.saveOrUpdate(orderInfo);
    }
}
