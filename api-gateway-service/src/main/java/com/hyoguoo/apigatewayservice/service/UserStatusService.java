package com.hyoguoo.apigatewayservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserStatusService {

    private static final String USER_STATUS_KEY_PREFIX = "user:status:";
    private static final String ACTIVE_STATUS = "ACTIVE";
    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public Mono<Boolean> isUserActive(Long userId) {
        String key = USER_STATUS_KEY_PREFIX + userId;

        return reactiveRedisTemplate.opsForValue()
                .get(key)
                .map(ACTIVE_STATUS::equalsIgnoreCase)
                .defaultIfEmpty(false)
                .onErrorReturn(false);
    }
}
