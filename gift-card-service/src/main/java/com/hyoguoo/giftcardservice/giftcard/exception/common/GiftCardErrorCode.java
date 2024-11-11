package com.hyoguoo.giftcardservice.giftcard.exception.common;

import com.hyoguoo.giftcardservice.core.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GiftCardErrorCode implements ErrorCode {

    GIFT_CARD_NOT_FOUND("E10001", "해당 기프트카드를 찾을 수 없습니다."),
    GIFT_CARD_USER_NOT_FOUND("E10002", "해당 사용자의 기프트카드를 찾을 수 없습니다."),
    GIFT_CARD_NAME_REQUIRED("E10003", "기프트카드 이름은 필수입니다."),
    QUANTITY_INVALID("E10004", "수량은 0보다 커야 합니다."),
    PRICE_INVALID("E10005", "가격은 0보다 커야 합니다."),
    TOTAL_BALANCE_INVALID("E10006", "총 잔액은 0보다 커야 합니다."),
    VALIDITY_DAYS_INVALID("E10007", "유효 기간은 0보다 커야 합니다."),
    SALE_START_AT_REQUIRED("E10008", "판매 시작일은 필수입니다."),
    GIFT_CARD_ID_REQUIRED("E10009", "기프트카드 ID는 필수입니다."),
    USER_ID_REQUIRED("E10010", "사용자 ID는 필수입니다."),
    PURCHASE_DATE_REQUIRED("E10011", "구매일은 필수입니다."),
    REMAINING_BALANCE_INVALID("E10012", "남은 잔액은 0보다 커야 합니다."),
    USER_STATUS_INVALID("E10013", "현재 상태에서 잔액을 사용할 수 없습니다."),
    EXPIRED_GIFT_CARD("E10014", "기프트카드가 만료되어 사용할 수 없습니다."),
    PURCHASE_DATE_INVALID("E10015", "기프트카드가 아직 구매되지 않았습니다."),
    USER_ID_MISMATCH("E10016", "사용자 ID가 일치하지 않습니다."),
    USAGE_AMOUNT_INVALID("E10017", "사용 금액은 0보다 커야 합니다."),
    INSUFFICIENT_BALANCE("E10018", "잔액이 부족합니다."),
    ;

    private final String code;
    private final String message;
}
