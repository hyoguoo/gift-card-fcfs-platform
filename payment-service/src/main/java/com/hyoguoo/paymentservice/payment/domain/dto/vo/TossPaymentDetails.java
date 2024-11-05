package com.hyoguoo.paymentservice.payment.domain.dto.vo;

import com.hyoguoo.paymentservice.payment.domain.dto.enums.TossPaymentStatus;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TossPaymentDetails {

    private final String orderName;
    private final Long totalAmount;
    private final TossPaymentStatus status;
    private final LocalDateTime approvedAt;
    private final String rawData;
}
