package com.github.Dementor0383;

import com.github.Dementor0383.annotation.After;
import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class TestRunner {

    // CR: it's more common to use verbs as method names
    public static void testRunner(Class<?> testClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        // CR: it's better to use List instead of ArrayList, see https://stackoverflow.com/questions/9852831/polymorphism-why-use-list-list-new-arraylist-instead-of-arraylist-list-n
        // CR: (but don't remove generics like in the answer to this question)
        ArrayList<Method> listOfBeforeAnnotation = new ArrayList<>();
        ArrayList<Method> listOfTestAnnotation = new ArrayList<>();
        ArrayList<Method> listOfAfterAnnotation = new ArrayList<>();
        int counterOfPassedTest = 0;
        int counterOfFailedTest = 0;
        int counterOfTests = 0;
        // CR: why do you need additional block here?
        {
            final Method[] declaredMethods = testClass.getDeclaredMethods();
            // CR: will fail for interface
            // CR: getDeclaredConstructors doesn't guarantee order, you need to find public constructor with no args.
            // CR: if it is not present you should warn user about it
            final Constructor<?> constructor = testClass.getDeclaredConstructors()[0];
            final Object constructorClass = constructor.newInstance();
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.getAnnotation((Before.class)) != null) {
                    listOfBeforeAnnotation.add(declaredMethod);
                    continue;
                }
                if (declaredMethod.getAnnotation(After.class) != null) {
                    listOfAfterAnnotation.add(declaredMethod);
                    continue;
                }
                if (declaredMethod.getAnnotation(Test.class) != null) {
                    listOfTestAnnotation.add(declaredMethod);
                }
            }
            System.out.println("______________New block of test:________________");
            for (Method testMethod : listOfTestAnnotation) {
                // CR: if it's static final test then your check will fail
                // CR: see https://en.wikipedia.org/wiki/Mask_(computing) and try operator `&`
                // CR: also test should have 0 params
                if (testMethod.getModifiers() == Modifier.PRIVATE) {//Modifier.Private ==2
                    // CR: please also write test name
                    // CR: probably better to write in System.err
                    System.out.println("Error PRIVATE modifier of test: can't run this test!");
                    continue;
                }
                runBeforeAnnotation(listOfBeforeAnnotation, constructorClass);
                try {
                    testMethod.invoke(constructorClass);
                    counterOfPassedTest++;
                } catch (InvocationTargetException e) {
                    counterOfFailedTest++;
                    // CR: from java doc: This method predates the general-purpose exception chaining facility.
                    // CR: The Throwable.getCause() method is now the preferred means of obtaining this information.
                    Class<?> target = e.getTargetException().getClass();
                    if (target.equals(testMethod.getAnnotation(Test.class).expected())) {
                        counterOfTests++;
                        for (Method afterMethod : listOfAfterAnnotation) {
                            afterMethod.invoke(constructorClass);
                        }
                        continue;
                    }
                    e.getTargetException().printStackTrace();
                }
                // CR: you should run them even if exception that is not InvocationTargetException appeared
                runAfterAnnotation(listOfAfterAnnotation, constructorClass);
                counterOfTests++;
            }
        }
        System.out.println("Quantity of passed test is: " + counterOfPassedTest + " of " + counterOfTests);
        System.out.println("Quantity of failed test is: " + counterOfFailedTest + " of " + counterOfTests);
    }

    private static void runBeforeAnnotation(ArrayList<Method> listOfBeforeAnnotation, Object constructorClass) throws IllegalAccessException {
        for (Method beforeMethod : listOfBeforeAnnotation) {
            try {
                // CR: 0 params
                if (beforeMethod.getModifiers() == Modifier.PRIVATE) {//Modifier.Private ==2
                    System.out.println("Error PRIVATE modifier of before annotation: can't run this before annotation!");
                    continue;
                }
                beforeMethod.invoke(constructorClass);
            } catch (InvocationTargetException e) {
                // CR: at least print e.getCause instead of InvocationTargetException
                e.printStackTrace();
            }
        }
    }

    private static void runAfterAnnotation(ArrayList<Method> listOfAfterAnnotation, Object constructorClass) throws IllegalAccessException {
        for (Method afterMethod : listOfAfterAnnotation) {
            try {
                if (afterMethod.getModifiers() == Modifier.PRIVATE) {//Modifier.Private ==2
                    System.out.println("Error PRIVATE modifier of after annotation: can't run after annotation!");
                    continue;
                }
                afterMethod.invoke(constructorClass);
            } catch (InvocationTargetException e) {
                // CR: info about during which method execution it happened would be nice
                e.printStackTrace();
            }
        }
    }
}
