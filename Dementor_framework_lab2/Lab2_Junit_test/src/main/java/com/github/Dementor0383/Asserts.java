package com.github.Dementor0383;

import com.github.Dementor0383.arraysEquals.ArrayTestEquals;
import com.github.Dementor0383.myException.FrameworkException;

public class Asserts {

    protected Asserts() {
    }

    public static void assertTrue(String failLine, boolean condition) {
        if (!condition) {
            throwFail(failLine);
        }
    }

    public static void throwFail(String failLine) {
        throw new FrameworkException(failLine);
    }

    public static void assertTrue(boolean condition) {
        assertTrue("Condition of variable is false", condition);
    }

    public static void assertFalse(String failLine, boolean condition) {
        assertTrue(failLine, !condition);
    }

    public static void assertFalse(boolean condition) {
        assertFalse("Condition of variable is false", condition);
    }

    public static void assertEquals(String failLine, String expected, String actual) {
        if (!expected.equals(actual)) {
            throwFail(formatOfMessage(failLine, expected, actual));
        }
    }

    public static void assertEquals(String expected, String actual) {
        assertEquals("The lines are different ", expected, actual);
    }

    public static void assertEquals(String failLine, int expected, int actual) {
        if (expected != actual) {
            throwFail(formatOfMessage(failLine, expected, actual));
        }
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals("Integer variable are different ", expected, actual);
    }

    public static void assertEquals(String failLine, long expected, long actual) {
        if (expected != actual) {
            throwFail(formatOfMessage(failLine, expected, actual));
        }
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals("Long variable are different ", expected, actual);
    }

    public static void assertEquals(String failLine, short expected, short actual) {
        if (expected != actual) {
            throwFail(formatOfMessage(failLine, expected, actual));
        }
    }

    public static void assertEquals(short expected, short actual) {
        assertEquals("Integer variable are different ", expected, actual);
    }

    public static void assertEquals(String failLine, float expected, float actual, float errorRate) {
        if (!((Math.abs(expected - actual)) <= errorRate)) {
            throwFail(formatOfMessage(failLine, expected, actual));
        }
    }

    public static void assertEquals(float expected, float actual, float errorRate) {
        assertEquals("Float variable are different ", expected, actual, errorRate);
    }

    public static void assertEquals(String failLine, double expected, double actual, double errorRate) {
        if (!((Math.abs(expected - actual)) <= errorRate)) {
            throwFail(formatOfMessage(failLine, Double.valueOf(expected), Double.valueOf(actual)));
        }
    }

    public static void assertEquals(double expected, double actual, double errorRate) {
        assertEquals("Double variable are different ", expected, actual, errorRate);
    }

    public static void assertEquals(double expected, double actual) {
        throwFail("Error: use double assert with error rate(assertEquals(failLine, expected, actual, errorRate))" +
                "to compare double-point numbers!");
    }

    public static void assertEquals(float expected, float actual) {
        throwFail("Error: use float assert with error rate(assertEquals(failLine, expected, actual, errorRate))" +
                "to compare double-point numbers!");
    }

    public static void assertEquals(String failLine, double expected, double actual) {
        throwFail("Error: use double assert with error rate(assertEquals(failLine, expected, actual, errorRate))" +
                "to compare double-point numbers!");
    }

    public static void assertEquals(String failLine, float expected, float actual) {
        throwFail("Error: use float assert with error rate(assertEquals(failLine, expected, actual, errorRate))" +
                "to compare double-point numbers!");
    }

    public static void assertEquals(String failLine, Object expected, Object actual) {
        if ((expected == null && actual != null) || (actual == null && expected != null) || !(expected.equals(actual))) {
            throwFail(formatOfMessage(failLine, expected, actual));
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals("Object variable are different", expected, actual);
    }

    public static void assertArrayEquals(String failLine, float[] expectedArray, float[] actualArray, float errorRate) {
        ArrayTestEquals.assertArrayEquals(failLine, expectedArray, actualArray, errorRate);
    }

    public static void assertArrayEquals(float[] expectedArray, float[] actualArray, float errorRate) {
        ArrayTestEquals.assertArrayEquals("Some elements at float array are different ", expectedArray, actualArray, errorRate);
    }

    public static void assertArrayEquals(String failLine, double[] expectedArray, double[] actualArray, double errorRate) {
        ArrayTestEquals.assertArrayEquals(failLine, expectedArray, actualArray, errorRate);
    }

    public static void assertArrayEquals(double[] expectedArray, double[] actualArray, double errorRate) {
        ArrayTestEquals.assertArrayEquals("Some elements at double array are different ", expectedArray, actualArray, errorRate);
    }

    public static void assertArrayEquals(String failLine, int[] expectedArray, int[] actualArray) {
        ArrayTestEquals.assertArrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(int[] expectedArray, int[] actualArray) {
        ArrayTestEquals.assertArrayEquals("Some elements at Integer array are different ", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, long[] expectedArray, long[] actualArray) {
        ArrayTestEquals.assertArrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(long[] expectedArray, long[] actualArray) {
        ArrayTestEquals.assertArrayEquals("Some elements at long array are different ", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, short[] expectedArray, short[] actualArray) {
        ArrayTestEquals.assertArrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(short[] expectedArray, short[] actualArray) {
        ArrayTestEquals.assertArrayEquals("Some elements at short array are different ", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, Object[] expectedArray, Object[] actualArray) {
        ArrayTestEquals.assertArrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(Object[] expectedArray, Object[] actualArray) {
        ArrayTestEquals.assertArrayEquals("Some elements at Object array are different ", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, char[] expectedArray, char[] actualArray) {
        ArrayTestEquals.assertArrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(char[] expectedArray, char[] actualArray) {
        ArrayTestEquals.assertArrayEquals("Some elements at Object array are different ", expectedArray, actualArray);
    }

    private static String formatOfMessage(String failLine, Object expected, Object actual) {
        String message = failLine != null && !failLine.equals("") ? failLine + " " : "";
        String expectedLine = String.valueOf(expected);
        String actualLine = String.valueOf(actual);
        if (expectedLine.equals(actualLine)) {
            return message + "expected value: " + formatOfClass(expected, expectedLine) + " but was: "
                    + formatOfClass(actual, actualLine);

        } else {
            return message + "expected value: <" + expectedLine + "> but was: <" + actualLine + ">";
        }
    }

    private static String formatOfClass(Object value, String valueLine) {
        String className;
        if (value == null) className = "null";
        else className = value.getClass().getName();
        return className + " value is <" + valueLine + ">!";
    }
}
