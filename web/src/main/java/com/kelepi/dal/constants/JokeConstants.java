package com.kelepi.dal.constants;

import java.util.Arrays;
import java.util.List;

public class JokeConstants {

    public static final int FRONT_PAGE_SZIE = 2;

    /**
     * 创建常量对象
     * @param <T>
     * @param value
     * @param message
     * @return
     */
    private static <T> NumberConstants<T> createNumber(T value, String message) {
        NumberConstants<T> number = new NumberConstants<T>();
        number.setMessage(message);
        number.setValue(value);
        return number;
    }
}
