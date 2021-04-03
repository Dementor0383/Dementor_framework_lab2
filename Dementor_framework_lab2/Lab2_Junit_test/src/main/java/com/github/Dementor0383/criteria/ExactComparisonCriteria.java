package com.github.Dementor0383.criteria;

import com.github.Dementor0383.Asserts;

public class ExactComparisonCriteria extends ComparisonCriteria {
    @Override
    protected void assertArraysElementEquals(Object expected, Object actual) {
        Asserts.assertEquals(expected, actual);
    }
}
