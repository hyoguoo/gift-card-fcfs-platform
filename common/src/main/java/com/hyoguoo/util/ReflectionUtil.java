package com.hyoguoo.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// TODO: 삭제 예정
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtil {

    public static void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
