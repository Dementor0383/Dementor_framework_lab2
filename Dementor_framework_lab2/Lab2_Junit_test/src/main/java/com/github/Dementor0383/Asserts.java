package com.github.Dementor0383;


import com.github.Dementor0383.criteria.ExactComparisonCriteria;
import com.github.Dementor0383.criteria.InexactComparisonCriteria;
import com.github.Dementor0383.myException.FrameworkException;

public class Asserts {

    protected Asserts() {
    }

    static public void assertTrue(String failLine, boolean condition) {
        if (!condition) {
            throwFail(failLine);
        }
    }

    static public void throwFail(String failLine) {
        if (failLine == null) {
            throw new FrameworkException();
        }
        throw new FrameworkException(failLine);
    }

    static public void throwFail() {
        throwFail(null);
    }

    static public void assertTrue(boolean condition) {
        assertTrue("Condition of variable is false", condition);
    }

    static public void assertFalse(String failLine, boolean condition) {
        assertTrue(failLine, !condition);
    }

    static public void assertFalse(boolean condition) {
        assertFalse("Condition of variable is false", condition);
    }

    //    Universal case
    static public void assertEquals(String failLine, Object expected, Object actual) {
        if (!checkForEqualsNull(expected, actual)) {
            throwFail(formatOfMessage(failLine, expected, actual));
        }
    }

    static public void assertEquals(Object expected, Object actual) {
        assertEquals("Value of some variables is not equals\n", expected, actual);
    }

    static public void assertNotEquals(String failLine, Object unexpected, Object actual) {
        if (checkForEqualsNull(unexpected, actual)) {
            throwFail(formatOfMessage(failLine, unexpected, actual));
        }
    }

    static public void assertNotEquals(Object unexpected, Object actual) {
        assertNotEquals("Value of some variables is equals\n", unexpected, actual);
    }
//

    private static boolean checkForEqualsNull(Object expected, Object actual) {
        if (expected == null)
            return actual == null;
        return expected.equals(actual);
    }

    //    double equals
    static public void assertEquals(String failLine, double expected, double actual, double errorRate) {
        if (doubleCompare(expected, actual, errorRate)) {
            throwFail(formatOfMessage(failLine, Double.valueOf(expected), Double.valueOf(actual)));
        }
    }

    static public void assertEquals(String failLine, double expected, double actual) {
        throwFail("Error: use double assert with error rate(assertEquals(failLine, expected, actual, errorRate))" +
                "to compare double-point numbers!");
    }

    static public void assertEquals(double expected, double actual) {
        assertEquals(null, expected, actual);
    }

    static public void assertEquals(double expected, double actual, double errorRate) {
        assertEquals("Variable of double type not equals\n", expected, actual, errorRate);
    }
//

    //    float equals
    static public void assertEquals(String failLine, float expected, float actual, float errorRate) {
        if (floatCompare(expected, actual, errorRate)) {
            throwFail(formatOfMessage(failLine, Float.valueOf(expected), Float.valueOf(actual)));
        }
    }

    static public void assertEquals(float expected, float actual, float errorRate) {
        assertEquals("Variable of float type not equals\n", expected, actual, errorRate);
    }

    static public void assertEquals(String failLine, float expected, float actual) {
        throwFail("Error: use double assert with error rate(assertEquals(failLine, expected, actual, errorRate))" +
                "to compare double-point numbers!");
    }

    static public void assertEquals(float expected, float actual) {
        assertEquals(null, expected, actual);
    }
//

    //        long equals
    static public void assertEquals(String failLine, long expected, long actual) {
        if (expected != actual) {
            throwFail(formatOfMessage(failLine, Long.valueOf(expected), Long.valueOf(actual)));
        }
    }

    static public void assertEquals(long expected, long actual) {
        assertEquals("Variable of long type not equals\n", expected, actual);
    }
//

    //    Object of one class equals
    public static void assertSameObject(String failLine, Object expected, Object actual) {
        if (expected == actual) {
            return;
        }
        String message = "";
        if (failLine != null) {
            message = failLine + " ";
        }
        throwFail(message + "expected object:<" + expected + "> was not actual object:<" + actual + ">!");
    }

    static public void assertSameObject(Object expected, Object actual) {
        assertSameObject("Variable of object type not equals\n", expected, actual);
    }
//

    //    Array asserts equals
    public static void assertArraysEquals(String failLine, float[] expectedArray,
                                          float[] actualArray, float errorRate) {
        new InexactComparisonCriteria(errorRate).arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArraysEquals(float[] expectedArray,
                                          float[] actualArray, float errorRate) {
        assertArraysEquals("Some elements of float arrays are not equals\n", expectedArray, actualArray, errorRate);
    }

    public static void assertArraysEquals(String failLine, double[] expectedArray,
                                          double[] actualArray, double errorRate) {
        new InexactComparisonCriteria(errorRate).arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArraysEquals(double[] expectedArray,
                                          double[] actualArray, double errorRate) {
        assertArraysEquals("Some elements of double arrays are not equals\n", expectedArray, actualArray, errorRate);
    }

    public static void assertArrayEquals(String failLine, long[] expectedArray, long[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(long[] expectedArray, long[] actualArray) {
        assertArrayEquals("Some elements of double arrays are not equals\n", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, short[] expectedArray, short[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(short[] expectedArray, short[] actualArray) {
        assertArrayEquals("Some elements of short arrays are not equals\n", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, char[] expectedArray, char[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(char[] expectedArray, char[] actualArray) {
        assertArrayEquals("Some elements of char arrays are not equals\n", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, boolean[] expectedArray, boolean[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        assertArrayEquals("Some elements of boolean arrays are not equals\n", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, int[] expectedArray, int[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(int[] expectedArray, int[] actualArray) {
        assertArrayEquals("Some elements of int arrays are not equals\n", expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, Object[] expectedArray, Object[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(Object[] expectedArray, Object[] actualArray) {
        assertArrayEquals("Some elements of object arrays are not equals\n", expectedArray, actualArray);
    }

    //
    static String formatOfMessage(String failLine, Object expected, Object actual) {
        String message = "";
        // CR: why not ternary?
        if (failLine != null && !failLine.equals("")) {
            message = failLine + " ";
        }
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

    // CR: why private static got swapped?
    static private boolean doubleCompare(double d1, double d2, double errorRate) {
        if (Double.compare(d1, d2) == 0) {//возвращает 0, если d1 и d2 численно равны
            return false;
        }
        //считает погрешность
        return !((Math.abs(d1 - d2)) <= errorRate);
    }

    static private boolean floatCompare(float f1, float f2, float errorRate) {
        if (Float.compare(f1, f2) == 0) {//возвращает 0, если d1 и d2 численно равны
            return false;
        }
        // CR: what's the reason to have compare before and not just use Math.abs...?
        // CR: if there's a reason please add a comment about it
        //считает погрешность
        return !((Math.abs(f1 - f2)) <= errorRate);
    }


}
