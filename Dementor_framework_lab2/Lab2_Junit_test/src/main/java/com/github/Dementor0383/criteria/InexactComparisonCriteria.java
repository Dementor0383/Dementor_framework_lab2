package com.github.Dementor0383.criteria;

import com.github.Dementor0383.Asserts;

public class InexactComparisonCriteria extends ComparisonCriteria {
    public Object currentDelta;

    public InexactComparisonCriteria(double delta) {
        currentDelta = delta;
    }

    public InexactComparisonCriteria(float delta) {
        currentDelta = delta;
    }

    @Override
    protected void assertArraysElementEquals(Object expected, Object actual) {
        if (expected instanceof Double) {
            Asserts.assertEquals((Double) expected, (Double) actual, (Double) currentDelta);
        } else if (expected instanceof Float) {
            Asserts.assertEquals((Float) expected, (Float) actual, (Float) currentDelta);
        }
    }
}
