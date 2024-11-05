package com.hyoguoo.paymentservice.payment.infrastructure.client;

import com.hyoguoo.paymentservice.payment.application.dto.command.TossPaymentConfirmCommand;
import com.hyoguoo.paymentservice.payment.application.port.TossPaymentGateway;
import com.hyoguoo.paymentservice.payment.domain.dto.TossPaymentInfo;
import com.hyoguoo.paymentservice.payment.domain.dto.enums.PaymentConfirmResultStatus;
import com.hyoguoo.paymentservice.payment.domain.dto.enums.TossPaymentStatus;
import com.hyoguoo.paymentservice.payment.domain.dto.vo.TossPaymentDetails;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class FakeTossPaymentGatewayImpl implements TossPaymentGateway {

    @Override
    public TossPaymentInfo getPaymentInfoByOrderId(String orderId) {
        return TossPaymentInfo.builder()
                .paymentKey("fake-payment-key")
                .orderId(orderId)
                .paymentConfirmResultStatus(PaymentConfirmResultStatus.SUCCESS)
                .paymentDetails(TossPaymentDetails.builder()
                        .orderName("Test Order")
                        .totalAmount(10000L)
                        .status(TossPaymentStatus.DONE)
                        .approvedAt(LocalDateTime.now())
                        .rawData("Raw payment data")
                        .build())
                .paymentFailure(null)
                .build();
    }

    @Override
    public TossPaymentInfo confirmPayment(TossPaymentConfirmCommand tossConfirmGatewayCommand, String idempotencyKey) {
        return TossPaymentInfo.builder()
                .paymentKey("fake-payment-key")
                .orderId(tossConfirmGatewayCommand.getOrderId())
                .paymentConfirmResultStatus(PaymentConfirmResultStatus.SUCCESS)
                .paymentDetails(TossPaymentDetails.builder()
                        .orderName("Test Order")
                        .totalAmount(10000L)
                        .status(TossPaymentStatus.DONE)
                        .approvedAt(LocalDateTime.now())
                        .rawData("Raw payment data")
                        .build())
                .paymentFailure(null)
                .build();
    }
}
