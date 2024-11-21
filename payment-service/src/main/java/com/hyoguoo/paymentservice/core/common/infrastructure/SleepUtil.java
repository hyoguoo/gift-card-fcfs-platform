package com.hyoguoo.paymentservice.core.common.infrastructure;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SleepUtil {

    private static final Random RANDOM = new Random();

    public static void sleepRandomTime(long minMillis, long maxMillis) {
        if (minMillis >= maxMillis || minMillis < 0) {
            throw new IllegalArgumentException("Invalid range: minMillis must be less than maxMillis and non-negative");
        }

        try {
            long sleepTime = minMillis + RANDOM.nextLong(maxMillis - minMillis);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalArgumentException("Thread was interrupted", e);
        }
    }
}
