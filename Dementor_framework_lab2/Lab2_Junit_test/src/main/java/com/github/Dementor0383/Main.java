package com.github.Dementor0383;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        // CR: 0 args - show usage
        for (String arg : args) {
            try {
                final Class<?> testClass = Class.forName(arg);
                TestRunner.testRunner(testClass);
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }


//
//
}
