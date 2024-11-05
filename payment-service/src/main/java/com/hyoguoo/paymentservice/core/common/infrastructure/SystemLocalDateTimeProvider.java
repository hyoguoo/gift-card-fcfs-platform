package com.hyoguoo.paymentservice.core.common.infrastructure;

import com.hyoguoo.paymentservice.core.common.application.port.LocalDateTimeProvider;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class SystemLocalDateTimeProvider implements LocalDateTimeProvider {

    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}