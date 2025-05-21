package org.example.utils;

import java.util.Random;

public class Utils {

    public static String randomString() {
        return randomString(10);
    }

    public static String randomString(int lenght) {
        Random random = new Random();
        int leftLimit = 97;
        int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(lenght);

        for (int i = 0; i < lenght; ++i) {
            int randomLimit = leftLimit + (int) (random.nextFloat() * (float) (rightLimit - leftLimit + 1));
            buffer.append(Character.toChars(randomLimit));
        }

        return buffer.toString();
    }
}