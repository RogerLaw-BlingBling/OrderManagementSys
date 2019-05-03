package com.ordersys.commons;

import java.util.Random;
import java.util.UUID;

public final class Randoms {
    private static final Random random = new Random(System.currentTimeMillis());
    private static final char[] alphaSet = "asdfghjklqwertyuiopzxcvbnm1234567890mnbvcxzlkjhgfdsapoiuytrewq0987654321".toCharArray();
    private static final char[] numberSet = "6574839201".toCharArray();

    public Randoms() {
    }

    public static String randomTimeId(long ms) {
        return String.format("%011d%06d", ms, random.nextInt(999999));
    }

    public static String randomTimeId() {
        return randomTimeId(System.currentTimeMillis());
    }

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String randomString(int length) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            stringBuilder.append(alphaSet[random.nextInt(alphaSet.length)]);
        }

        return stringBuilder.toString();
    }

    public static String randomNumber(int length) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            stringBuilder.append(numberSet[random.nextInt(numberSet.length)]);
        }

        return stringBuilder.toString();
    }
}
