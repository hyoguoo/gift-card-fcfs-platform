package com.hyoguoo.orderservice.order.presentation.port;

public interface OrderProcessService {

    void completedOrderInfo(Long orderInfoId);

    void failedOrderInfo(Long orderInfoId);
}
