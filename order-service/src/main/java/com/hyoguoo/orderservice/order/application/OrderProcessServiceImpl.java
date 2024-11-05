package com.hyoguoo.orderservice.order.application;

import com.hyoguoo.orderservice.order.application.usecase.OrderInfoLoadUseCase;
import com.hyoguoo.orderservice.order.application.usecase.OrderInfoProcessUseCase;
import com.hyoguoo.orderservice.order.domain.OrderInfo;
import com.hyoguoo.orderservice.order.presentation.port.OrderProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProcessServiceImpl implements OrderProcessService {

    private final OrderInfoLoadUseCase orderInfoLoadUseCase;
    private final OrderInfoProcessUseCase orderInfoProcessUseCase;

    @Override
    public void completedOrderInfo(Long orderInfoId) {
        OrderInfo orderInfo = orderInfoLoadUseCase.loadOrderInfo(orderInfoId);
        orderInfoProcessUseCase.completedOrderInfo(orderInfo);
    }

    @Override
    public void failedOrderInfo(Long orderInfoId) {
        OrderInfo orderInfo = orderInfoLoadUseCase.loadOrderInfo(orderInfoId);
        orderInfoProcessUseCase.failedOrderInfo(orderInfo);
    }
}
