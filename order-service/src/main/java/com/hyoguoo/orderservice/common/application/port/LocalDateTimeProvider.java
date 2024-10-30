package com.hyoguoo.orderservice.common.application.port;

import java.time.LocalDateTime;

public interface LocalDateTimeProvider {

    LocalDateTime now();
}
