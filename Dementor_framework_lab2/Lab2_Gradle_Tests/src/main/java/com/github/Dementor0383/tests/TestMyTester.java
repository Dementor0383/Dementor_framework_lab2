package com.github.Dementor0383.tests;

import com.github.Dementor0383.Asserts;
import com.github.Dementor0383.annotation.After;
import com.github.Dementor0383.annotation.Before;
import com.github.Dementor0383.annotation.Test;
import com.github.Dementor0383.myException.FrameworkException;

public class TestMyTester {

    private static int counterOfTest = 0;

    public String createStringLine(String line) {
        return "Current line is <" + line + ">!";
    }

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
    public void testStringAssert() {
        String actualLine = createStringLine("Test Line");
        String expectedLine = "Current line123 is <Test Line>!";
        Asserts.assertEquals(expectedLine, actualLine);
    }

    @Test
    public void testFloatAssert() {
        float expected = 0;
        float actual = 4;
        float errorRate = 0;
        Asserts.assertEquals(expected, actual, errorRate);
    }

    @Test
    public void testArrayAssert() {
        int[] actualArray = new int[3];
        int[] expectedArray = new int[3];
        actualArray[0] = 1;
        actualArray[1] = 2;
        actualArray[2] = 3;
        expectedArray[0] = 1;
        expectedArray[1] = 2;
        expectedArray[2] = 3;
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testFailArrayAssert() {
        int[] actualArray = new int[3];
        int[] expectedArray = new int[3];
        actualArray[0] = 1;
        actualArray[1] = 2;
        actualArray[2] = 3;
        expectedArray[0] = 0;
        expectedArray[1] = 2;
        expectedArray[2] = 3;
        Asserts.assertArrayEquals("Not equals arrays", expectedArray, actualArray);
    }

    @Test
    public void testWithoutFailLineFailArrayAssert() {
        int[] actualArray = new int[3];
        int[] expectedArray = new int[3];
        actualArray[0] = 1;
        actualArray[1] = 2;
        actualArray[2] = 3;
        expectedArray[0] = 0;
        expectedArray[1] = 2;
        expectedArray[2] = 3;
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test(expected = FrameworkException.class)
    public void testBoolAssert() {
        Asserts.assertTrue("Variable test is false", false);
    }

    @Test
    public void testFailBoolAssert() {
        Asserts.assertTrue("Variable test is false", false);
    }

    @Test
    public void testAssertObjectArray() {
        TokenType[] expectedArray = new TokenType[3];
        TokenType[] actualArray = new TokenType[3];
        actualArray[0] = TokenType.EOF;
        actualArray[1] = TokenType.TESTCASE;
        actualArray[2] = TokenType.AT;
        expectedArray[0] = TokenType.EOF;
        expectedArray[1] = TokenType.EOL;
        expectedArray[2] = TokenType.TEST_SUITE;
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    private void testPrivateAssertObjectArray() {
        TokenType[] expectedArray = new TokenType[3];
        TokenType[] actualArray = new TokenType[3];
        actualArray[0] = TokenType.EOF;
        actualArray[1] = TokenType.TESTCASE;
        actualArray[2] = TokenType.AT;
        expectedArray[0] = TokenType.EOF;
        expectedArray[1] = TokenType.EOL;
        expectedArray[2] = TokenType.TEST_SUITE;
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }
}
