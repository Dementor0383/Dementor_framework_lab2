package com.github.Dementor0383.criteria;

import com.github.Dementor0383.Asserts;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class ComparisonCriteria {

    public void arrayEquals(String failLine, Object expectedArray, Object actualArray) {
        if (expectedArray == actualArray ||
                Arrays.deepEquals(new Object[]{expectedArray}, new Object[]{actualArray})) {
            return;
        }

        int lengthExpArray = checkArraysLength(failLine, expectedArray, actualArray);

        for (int i = 0; i < lengthExpArray; i++) {
            Object expectedLocalArray = Array.get(expectedArray, i);
            Object actualLocalArray = Array.get(actualArray, i);

            if (expectedLocalArray.getClass().isArray() && expectedLocalArray != null &&
                    actualLocalArray.getClass().isArray() && actualLocalArray != null) {
//                Array with arrays
                arrayEquals(failLine, expectedLocalArray, actualLocalArray);
            } else {
                assertArraysElementEquals(expectedLocalArray, actualLocalArray);
            }
        }

    }


    private int checkArraysLength(String failLine, Object expectedArray, Object actualArray) {
        String message = "";
        if (failLine != null) message = failLine + ": ";
        if (expectedArray == null) {
            Asserts.throwFail(message + "expected array is null!");
        }
        if (actualArray == null) {
            Asserts.throwFail(message + "actual array is null!");
        }
        int lengthExpArray = Array.getLength(expectedArray);
        int lengthActArray = Array.getLength(actualArray);
        if (lengthExpArray != lengthActArray) {
            Asserts.throwFail(message + "Expected and Actual arrays have different length, expected array's length: "
                    + lengthExpArray + " but actual array's length: " + lengthActArray);
        }
        return lengthExpArray;
    }

    protected abstract void assertArraysElementEquals(Object expected, Object actual);
}
