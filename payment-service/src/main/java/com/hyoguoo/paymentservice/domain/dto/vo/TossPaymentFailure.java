package com.hyoguoo.paymentservice.domain.dto.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TossPaymentFailure {

    private final String code;
    private final String message;
}
