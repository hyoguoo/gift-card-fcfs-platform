package com.hyoguoo.paymentservice.payment.exception.common;

import com.hyoguoo.paymentservice.core.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentErrorCode implements ErrorCode {

    PAYMENT_NOT_FOUND("E30001", "결제 정보를 찾을 수 없습니다."),
    ORDERED_GIFT_CARD_STOCK_NOT_ENOUGH("E30002", "주문한 기프트카드 재고가 부족합니다."),
    INVALID_EXECUTION_STATUS("E30003", "결제 실행할 수 없는 상태입니다."),
    BUYER_ID_NOT_MATCH("E30004", "구매자 ID가 일치하지 않습니다."),
    PAYMENT_KEY_NOT_MATCH("E30005", "결제 키가 일치하지 않습니다."),
    INVALID_TOTAL_AMOUNT("E30006", "유효하지 않은 총 주문 금액입니다."),
    INVALID_ORDER_ID("E30007", "유효하지 않은 주문 ID입니다."),
    INVALID_STATUS_TO_EXECUTE("E30008", "결제 실행할 수 없는 상태입니다."),
    TOSS_RETRYABLE_ERROR("E30009", "Toss 결제에서 재시도 가능한 오류가 발생했습니다."),
    TOSS_NON_RETRYABLE_ERROR("E30010", "Toss 결제에서 재시도 불가능한 오류가 발생했습니다."),
    INVALID_STATUS_TO_CONFIRM("E30011", "결제 승인할 수 없는 상태입니다."),
    INVALID_STATUS_TO_DONE("E30012", "결제 완료할 수 없는 상태입니다."),
    INVALID_STATUS_TO_FAIL("E30013", "결제 실패할 수 없는 상태입니다."),
    INVALID_STATUS_TO_UNKNOWN("E30014", "결제를 알 수 없는 상태로 변경할 수 없는 상태입니다."),
    LOCK_NOT_ACQUIRED("E30015", "분산락을 획득할 수 없습니다."),
    LOCK_ACQUIRE_INTERRUPTED("E30016", "분산락 획득 중 인터럽트가 발생했습니다."),
    UNKNOWN_LOCK_ERROR("E30017", "분산락 획득 중 알 수 없는 오류가 발생했습니다."),
    ;

    private final String code;
    private final String message;
}
