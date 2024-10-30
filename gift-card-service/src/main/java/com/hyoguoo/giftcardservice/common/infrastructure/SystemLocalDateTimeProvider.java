package com.hyoguoo.giftcardservice.common.infrastructure;

import com.hyoguoo.giftcardservice.common.application.port.LocalDateTimeProvider;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SystemLocalDateTimeProvider implements LocalDateTimeProvider {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
