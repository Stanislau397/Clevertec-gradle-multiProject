package ru.clevertec.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private final Utils utils;

    public UtilsTest() {
        this.utils = new Utils();
    }

    @Test
    void isAllPositiveNumbersWillReturnTrue() {
        //given
        String[] numbers = {"11", "12"};
        //when
        boolean condition = utils.isAllPositiveNumbers(numbers);
        //then
        assertTrue(condition);
    }
}