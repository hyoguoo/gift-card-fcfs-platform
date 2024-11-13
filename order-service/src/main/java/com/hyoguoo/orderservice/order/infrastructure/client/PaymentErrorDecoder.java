package com.hyoguoo.orderservice.order.infrastructure.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyoguoo.orderservice.order.exception.OrderErrorDecodeException;
import com.hyoguoo.orderservice.order.exception.OrderPaymentException;
import com.hyoguoo.orderservice.order.exception.common.OrderErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.util.Objects;

public class PaymentErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String s, Response response) {
        try {
            PaymentErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(),
                    PaymentErrorResponse.class);
            PaymentErrorCode errorCode = PaymentErrorCode.of(errorResponse.getCode());
            if (Objects.requireNonNull(errorCode) == PaymentErrorCode.INTERNAL_SERVER_ERROR) {
                return OrderPaymentException.of(OrderErrorCode.PAYMENT_INTERNAL_SERVER_ERROR);
            } else {
                return OrderPaymentException.of(OrderErrorCode.PAYMENT_UNKNOWN_ERROR);
            }
        } catch (Exception e) {
            return OrderErrorDecodeException.of(OrderErrorCode.DECODE_ERROR);
        }
    }
}
