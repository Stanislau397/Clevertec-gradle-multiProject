package ru.clevertec.api;

import ru.clevertec.core.Utils;

public class App {

    public static void main(String[] args) {
        Utils utils = new Utils();
        System.out.println(utils.isAllPositiveNumbers("12", "11", "-11"));
    }
}
