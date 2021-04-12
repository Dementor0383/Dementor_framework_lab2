package com.github.Dementor0383;

import com.github.Dementor0383.annotation.After;
import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void runTests(Class<?> testClass) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        List<Method> listOfBeforeAnnotation = new ArrayList<>();
        List<Method> listOfTestAnnotation = new ArrayList<>();
        List<Method> listOfAfterAnnotation = new ArrayList<>();
        int counterOfPassedTest = 0;
        int counterOfFailedTest = 0;
        int counterOfTests = 0;
        boolean checkConstructor = false;
        int indexOfConstructor = 0;
        final Method[] declaredMethods = testClass.getDeclaredMethods();
        final Constructor<?>[] constructors = testClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == 0) {
                checkConstructor = true;
                if (constructor.getModifiers() == Modifier.PUBLIC) {
                    break;
                }
            }
            indexOfConstructor++;
        }
        Constructor<?> constructor = constructors[indexOfConstructor];
        if (!checkConstructor) {
            throw new IllegalAccessException("Error: There is no constructor with no arguments for your test class " + testClass.getName() + " ! Please add a public constructor without arguments!");
        }
        if (constructor.getModifiers() != Modifier.PUBLIC) {
            throw new IllegalAccessException("Error: There is no constructor with the PUBLIC modifier for your test class " + testClass.getName() + " ! Please add a public constructor without arguments!");
        }
        final Object constructorClass = constructor.newInstance();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getAnnotation((Before.class)) != null) {
                if (!checkArgModAnnotation(declaredMethod, "BEFORE")) {
                    continue;
                }
                listOfBeforeAnnotation.add(declaredMethod);
                continue;
            }
            if (declaredMethod.getAnnotation(After.class) != null) {
                if (!checkArgModAnnotation(declaredMethod, "AFTER")) {
                    continue;
                }
                listOfAfterAnnotation.add(declaredMethod);
                continue;
            }
            if (declaredMethod.getAnnotation(Test.class) != null) {
                listOfTestAnnotation.add(declaredMethod);
            }
        }
        System.out.println("______________New block of test:________________");
        for (Method testMethod : listOfTestAnnotation) {
            boolean checkMethod = checkArgModAnnotation(testMethod, "TEST");
            if (!checkMethod) continue;
            runBeforeAnnotation(listOfBeforeAnnotation, constructorClass);
            try {
                testMethod.invoke(constructorClass);
                counterOfPassedTest++;
            } catch (InvocationTargetException e) {
                counterOfFailedTest++;
                Class<?> target = e.getCause().getClass();
                if (target.equals(testMethod.getAnnotation(Test.class).expected())) {
                    continue;
                }
                e.getCause().printStackTrace();//!!!getCause
            } finally {
                runAfterAnnotation(listOfAfterAnnotation, constructorClass);
                counterOfTests++;
            }
        }
        System.out.println("Quantity of passed test is: " + counterOfPassedTest + " of " + counterOfTests);
        System.out.println("Quantity of failed test is: " + counterOfFailedTest + " of " + counterOfTests);
    }

    private static void runBeforeAnnotation(List<Method> listOfBeforeAnnotation, Object constructorClass) throws IllegalAccessException {
        for (Method beforeMethod : listOfBeforeAnnotation) {
            try {
                beforeMethod.invoke(constructorClass);
            } catch (InvocationTargetException e) {
                System.out.println("Cause of Exception in method " + beforeMethod.getName() + " : " + e.getCause());
            }
        }
    }

    private static void runAfterAnnotation(List<Method> listOfAfterAnnotation, Object constructorClass) throws IllegalAccessException {
        for (Method afterMethod : listOfAfterAnnotation) {
            try {
                afterMethod.invoke(constructorClass);
            } catch (InvocationTargetException e) {
                System.out.println("Cause of Exception in method " + afterMethod.getName() + " : " + e.getCause());
            }
        }
    }

    private static boolean checkArgModAnnotation(Method method, String nameOfAnnotation) {
        if (method.getModifiers() == Modifier.PRIVATE) {//Modifier.Private ==2
            System.out.println("Error PRIVATE modifier of " + method.getName() + " : can't run this method with annotation " + nameOfAnnotation + " !");
            return false;
        }
        if (method.getParameterCount() != 0) {
            System.out.println("Error: method " + method.getName() + " with " + nameOfAnnotation + " annotation mustn't have arguments!");
            return false;
        }
        if (method.getModifiers() == (Modifier.PRIVATE | Modifier.STATIC)) {//static private method
            System.out.println("Error STATIC PRIVATE modifier of " + method.getName() + " : can't run this method with annotation " + nameOfAnnotation + " !");
            return false;
        }
        if (method.getModifiers() == (Modifier.PRIVATE | Modifier.FINAL)) {//final private method
            System.out.println("Error FINAL PRIVATE modifier of " + method.getName() + " : can't run this method with annotation " + nameOfAnnotation + " !");
            return false;
        }
        return true;
    }
}
