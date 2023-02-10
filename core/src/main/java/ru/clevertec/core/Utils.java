package ru.clevertec.core;

import ru.clevertec.util.StringUtils;

import java.util.Arrays;

public class Utils {

    private final StringUtils stringUtils = new StringUtils();

    public boolean isAllPositiveNumbers(String... numbers) {
        return Arrays.stream(numbers).allMatch(stringUtils::isPositiveNumber);
    }
}
