package com.hyoguoo.paymentservice.domain.dto;

import com.hyoguoo.paymentservice.domain.dto.enums.PaymentConfirmResultStatus;
import com.hyoguoo.paymentservice.domain.dto.vo.TossPaymentDetails;
import com.hyoguoo.paymentservice.domain.dto.vo.TossPaymentFailure;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TossPaymentInfo {

    private final String paymentKey;
    private final String orderId;
    private final PaymentConfirmResultStatus paymentConfirmResultStatus;
    private final TossPaymentDetails paymentDetails;
    private final TossPaymentFailure paymentFailure;
}

