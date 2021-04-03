package com.github.Dementor0383.tests;

import com.github.Dementor0383.Asserts;
import com.github.Dementor0383.annotation.After;
import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;

public class TestMyTesterSecondClass {

    private static int counterOfTest = 0;

    @Before
    public void printBeforeOne() {
        System.out.println("Test number " + counterOfTest + " start");
    }

    @After
    public void printAfterOne() {
        counterOfTest++;
        System.out.println("Test finish");
    }

    @Test
    public void testArrayAssert() {
        char[] actualArray = new char[3];
        char[] expectedArray = new char[3];
        actualArray[0] = '!';
        actualArray[1] = 'g';
        actualArray[2] = 'h';
        expectedArray[0] = '!';
        expectedArray[1] = 'g';
        expectedArray[2] = '?';
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testStringAssert() {
        String actualLine = "Test Line";
        String expectedLine = "Current line123 is <Test Line>!";
        Asserts.assertEquals(expectedLine, actualLine);
    }
}
