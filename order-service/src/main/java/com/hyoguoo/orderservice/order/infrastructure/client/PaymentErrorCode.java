package com.hyoguoo.orderservice.order.infrastructure.client;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentErrorCode {

    INTERNAL_SERVER_ERROR("E99999", "Payment 서버 내부 오류입니다."),
    ;

    private final String code;
    private final String message;

    public static PaymentErrorCode of(String code) {
        return Arrays.stream(PaymentErrorCode.values())
                .filter(s -> s.getCode().equals(code))
                .findFirst()
                .orElseThrow();
    }
}
