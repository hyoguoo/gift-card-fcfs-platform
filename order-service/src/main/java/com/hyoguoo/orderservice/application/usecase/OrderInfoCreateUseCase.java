package com.hyoguoo.orderservice.application.usecase;

import com.hyoguoo.orderservice.application.dto.command.CheckoutCommand;
import com.hyoguoo.orderservice.application.port.OrderInfoRepository;
import com.hyoguoo.orderservice.common.application.port.LocalDateTimeProvider;
import com.hyoguoo.orderservice.common.application.port.UUIDProvider;
import com.hyoguoo.orderservice.domain.OrderInfo;
import com.hyoguoo.orderservice.domain.dto.GiftCardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderInfoCreateUseCase {

    private final OrderInfoRepository orderInfoRepository;
    private final UUIDProvider uuidProvider;
    private final LocalDateTimeProvider localDateTimeProvider;

    public OrderInfo createOrderInfo(CheckoutCommand command, GiftCardInfo giftCardInfo) {
        OrderInfo orderInfo = OrderInfo.requiredArgsBuilder()
                .command(command)
                .giftCardInfo(giftCardInfo)
                .orderId(uuidProvider.generateUUID())
                .orderedAt(localDateTimeProvider.now())
                .requiredArgsBuild();

        return orderInfoRepository.saveOrUpdate(orderInfo);
    }
}
