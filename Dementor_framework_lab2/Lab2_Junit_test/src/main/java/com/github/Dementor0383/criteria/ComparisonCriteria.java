package com.github.Dementor0383.criteria;

import com.github.Dementor0383.Asserts;

import java.lang.reflect.Array;
import java.util.Arrays;

public abstract class ComparisonCriteria {

    // CR: probably all this methods should be static
    // CR: they are already arrays, probably it's better to have String, Object[] and Object[] as params
    public void arrayEquals(String failLine, Object expectedArray, Object actualArray) {
        if (expectedArray == actualArray ||
                Arrays.deepEquals(new Object[]{expectedArray}, new Object[]{actualArray})) {
            return;
        }

        int lengthExpArray = checkArraysLength(failLine, expectedArray, actualArray);

        for (int i = 0; i < lengthExpArray; i++) {
            // CR: probably `nested` or `inner`, not `local`
            Object expectedLocalArray = Array.get(expectedArray, i);
            Object actualLocalArray = Array.get(actualArray, i);

            // CR: you'll get npe if expectedLocalArray or actualLocalArray is null
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
        // CR: why not String message = failLine == null ? "" : message = failLine + ": ";
        // CR: also why not construct it once instead of doing everytime in recursion
        // CR: e.g. it can be constructed only when we found failure
        if (failLine != null) message = failLine + ": ";
        // CR: this can happen only for top level array, no need to check in recursion
        if (expectedArray == null) {
            // CR: can just throw I guess, without extra call
            Asserts.throwFail(message + "expected array is null!");
        }
        if (actualArray == null) {
            Asserts.throwFail(message + "actual array is null!");
        }
        // CR: expectedArray.length is better since it's typed. you should cast somewhere before
        int lengthExpArray = Array.getLength(expectedArray);
        int lengthActArray = Array.getLength(actualArray);
        if (lengthExpArray != lengthActArray) {
            // CR: probably you should also mention current depth of recursion
            Asserts.throwFail(message + "Expected and Actual arrays have different length, expected array's length: "
                    + lengthExpArray + " but actual array's length: " + lengthActArray);
        }
        return lengthExpArray;
    }

    protected abstract void assertArraysElementEquals(Object expected, Object actual);
}
