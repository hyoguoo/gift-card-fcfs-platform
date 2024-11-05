package com.hyoguoo.kafka.message;

import com.hyoguoo.kafka.message.enums.EventType;
import com.hyoguoo.kafka.message.vo.GiftCardUserEventData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GiftCardUserEventMessage {

    private EventType eventType;
    private GiftCardUserEventData data;
}
