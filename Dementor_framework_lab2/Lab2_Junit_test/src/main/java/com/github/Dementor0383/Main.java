package com.github.Dementor0383;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalStateException("Not enough program arguments\n" + "Please use the next one form: \n" +
                    "java -cp <your-junit-jar>;<tested-classes> <your-main-class> class-name_1 [class-name] class-name_2 [class-name] ....\n");
        }
        for (String arg : args) {
            try {
                final Class<?> testClass = Class.forName(arg);
                TestRunner.runTests(testClass);
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException | InvocationTargetException e) {
                System.out.println("Cause of Exception: " + e.getCause());
            }
        }

    }

}
