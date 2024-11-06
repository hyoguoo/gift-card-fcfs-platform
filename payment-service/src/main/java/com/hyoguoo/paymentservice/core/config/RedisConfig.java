package com.hyoguoo.paymentservice.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.script.DefaultRedisScript;

@Configuration
public class RedisConfig {

    private static final String DECREASE_STOCK_SCRIPT = """
            local stock = redis.call('GET', KEYS[1])
            stock = tonumber(stock)
            if stock - tonumber(ARGV[1]) < 0 then
                return -1
            end
            redis.call('DECRBY', KEYS[1], ARGV[1])
            return stock - tonumber(ARGV[1])
            """;

    private static final String INCREASE_STOCK_SCRIPT = """
            return redis.call('INCRBY', KEYS[1], tonumber(ARGV[1]))
            """;

    @Bean
    public DefaultRedisScript<Long> decreaseStockScript() {
        return createRedisScript(DECREASE_STOCK_SCRIPT);
    }

    @Bean
    public DefaultRedisScript<Long> increaseStockScript() {
        return createRedisScript(INCREASE_STOCK_SCRIPT);
    }

    private DefaultRedisScript<Long> createRedisScript(String script) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();

        redisScript.setScriptText(script);
        redisScript.setResultType(Long.class);

        return redisScript;
    }
}
