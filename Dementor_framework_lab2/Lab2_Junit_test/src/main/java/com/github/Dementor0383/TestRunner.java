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

    public static void testRunner(Class<?> testClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        ArrayList<Method> listOfBeforeAnnotation = new ArrayList<>();
        ArrayList<Method> listOfTestAnnotation = new ArrayList<>();
        ArrayList<Method> listOfAfterAnnotation = new ArrayList<>();
        int counterOfPassedTest = 0;
        int counterOfFailedTest = 0;
        int counterOfTests = 0;
        {
            final Method[] declaredMethods = testClass.getDeclaredMethods();
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
                if (testMethod.getModifiers() == Modifier.PRIVATE) {//Modifier.Private ==2
                    System.out.println("Error PRIVATE modifier of test: can't run this test!");
                    continue;
                }
                runBeforeAnnotation(listOfBeforeAnnotation, constructorClass);
                try {
                    testMethod.invoke(constructorClass);
                    counterOfPassedTest++;
                } catch (InvocationTargetException e) {
                    counterOfFailedTest++;
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
                if (beforeMethod.getModifiers() == Modifier.PRIVATE) {//Modifier.Private ==2
                    System.out.println("Error PRIVATE modifier of before annotation: can't run this before annotation!");
                    continue;
                }
                beforeMethod.invoke(constructorClass);
            } catch (InvocationTargetException e) {
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
                e.printStackTrace();
            }
        }
    }
}
