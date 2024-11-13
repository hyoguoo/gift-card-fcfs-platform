package com.hyoguoo.orderservice.order.exception.common;

import com.hyoguoo.orderservice.core.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {

    BUYER_ID_REQUIRED("E21001", "구매자 ID는 필수입니다."),
    GIFT_CARD_ID_REQUIRED("E21002", "기프트카드 ID는 필수입니다."),
    PAYMENT_AMOUNT_INVALID("E21003", "결제 금액은 0 이상이어야 합니다."),
    ORDER_ID_REQUIRED("E21004", "주문 ID는 필수입니다."),
    ORDERED_AT_REQUIRED("E21005", "주문 일시는 필수입니다."),
    ORDER_BEFORE_SALE_START("E21006", "주문은 판매 시작일 이전에 할 수 없습니다."),
    ORDER_STATUS_PENDING_REQUIRED("E21007", "주문 상태가 PENDING이어야 합니다."),
    ORDER_STATUS_PENDING_OR_COMPLETED_REQUIRED("E21008", "주문 상태가 PENDING 또는 COMPLETED여야 합니다."),
    ORDER_STATUS_PENDING_OR_FAILED_REQUIRED("E21009", "주문 상태가 PENDING 또는 FAILED여야 합니다."),
    ORDER_NOT_FOUND("E21010", "주문을 찾을 수 없습니다."),
    PAYMENT_INTERNAL_SERVER_ERROR("E21011", "결제 서버 내부 오류입니다."),
    PAYMENT_UNKNOWN_ERROR("E21012", "결제 서버 알 수 없는 오류입니다."),
    DECODE_ERROR("E21013", "응답 디코딩 중 오류가 발생했습니다."),
    ;

    private final String code;
    private final String message;
}
