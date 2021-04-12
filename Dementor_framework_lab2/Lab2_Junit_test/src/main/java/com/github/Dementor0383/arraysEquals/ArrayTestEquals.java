package com.github.Dementor0383.arraysEquals;

import com.github.Dementor0383.Asserts;

import java.lang.reflect.Array;

public class ArrayTestEquals {

    public static void assertArrayEquals(String failLine, long[] expectedArray, long[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, int[] expectedArray, int[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, short[] expectedArray, short[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, char[] expectedArray, char[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, String[] expectedArray, String[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, Object[] expectedArray, Object[] actualArray) {
        new ExactComparisonCriteria().arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, double[] expectedArray, double[] actualArray, double errorRate) {
        new InexactComparisonCriteria(errorRate).arrayEquals(failLine, expectedArray, actualArray);
    }

    public static void assertArrayEquals(String failLine, float[] expectedArray, float[] actualArray, float errorRate) {
        new InexactComparisonCriteria(errorRate).arrayEquals(failLine, expectedArray, actualArray);
    }

    private static abstract class ComparisonCriteria {

        public void arrayEquals(String failLine, Object expectedArray, Object actualArray) {

            if (expectedArray == null || actualArray == null) {
                throw new IllegalArgumentException("Expected or Actual array is null!");
            }

            if (!expectedArray.getClass().isArray())
                throw new IllegalArgumentException("Expected object is not an array!");
            if (!actualArray.getClass().isArray()) throw new IllegalArgumentException("Actual object is not an array!");

            int lengthExpArray = getArrayLength(failLine, expectedArray, actualArray);

            for (int i = 0; i < lengthExpArray; i++) {
                Object expectedNestedArray = Array.get(expectedArray, i);
                Object actualNestedArray = Array.get(actualArray, i);

                if (expectedNestedArray == null) {
                    Asserts.throwFail("The expected array element under the number is " + i + " null");
                }
                if (actualNestedArray == null) {
                    Asserts.throwFail("The actual array element under the number is " + i + " null");
                }
                if (expectedNestedArray.getClass().isArray() && actualNestedArray.getClass().isArray()) {
                    arrayEquals(failLine, expectedNestedArray, actualNestedArray);
                } else {
                    assertArraysElements(expectedNestedArray, actualNestedArray);
                }
            }
        }

        public int getArrayLength(String failLine, Object expectedArray, Object actualArray) {
            if (expectedArray == null) {
                Asserts.throwFail(failLine + ": expected array is null!");
            }
            if (actualArray == null) {
                Asserts.throwFail(failLine + ": actual array is null!");
            }
            if (Array.getLength(expectedArray) != Array.getLength(actualArray)) {
                Asserts.throwFail(failLine + " Expected and Actual arrays have different length, expected array's length: " +
                        +Array.getLength(expectedArray) + "but actual array 's length: " + Array.getLength(actualArray));
            }
            return Array.getLength(expectedArray);
        }

        protected abstract void assertArraysElements(Object expected, Object actual);

    }

    private static class ExactComparisonCriteria extends ComparisonCriteria {

        @Override
        protected void assertArraysElements(Object expected, Object actual) {
            Asserts.assertEquals(expected, actual);
        }
    }

    private static class InexactComparisonCriteria extends ComparisonCriteria {
        public Object currentErrorRate;

        public InexactComparisonCriteria(float floatErrorRate) {
            currentErrorRate = floatErrorRate;
        }

        public InexactComparisonCriteria(double doubleErrorRate) {
            currentErrorRate = doubleErrorRate;
        }

        @Override
        protected void assertArraysElements(Object expected, Object actual) {
            if (expected instanceof Double) {
                Asserts.assertEquals((Double) expected, (Double) actual, (Double) currentErrorRate);
            } else {
                Asserts.assertEquals((Float) expected, (Float) actual, (Float) currentErrorRate);
            }
        }
    }

}
