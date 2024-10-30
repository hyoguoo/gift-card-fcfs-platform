package com.hyoguoo.paymentservice.common.infrastructure;

import com.hyoguoo.paymentservice.common.application.port.UUIDProvider;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class SystemUUIDProvider implements UUIDProvider {

    @Override
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
