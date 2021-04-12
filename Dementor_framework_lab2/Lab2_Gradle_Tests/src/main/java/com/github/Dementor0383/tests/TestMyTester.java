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
        int[] actualArray = {1, 2, 3};
        int[] expectedArray = {1, 2, 3};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testFailArrayAssert() {
        int[] actualArray = {1, 2, 3};
        int[] expectedArray = {0, 2, 3};
        Asserts.assertArrayEquals("Not equals arrays", expectedArray, actualArray);
    }

    @Test
    public void testWithoutFailLineFailArrayAssert() {
        int[] actualArray = {1, 2, 3};
        int[] expectedArray = {0, 2, 3};
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
        TokenType[] expectedArray = {TokenType.EOF, TokenType.TESTCASE, TokenType.AT};
        TokenType[] actualArray = {TokenType.EOF, TokenType.EOL, TokenType.TEST_SUITE};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    private void testPrivateAssertObjectArray() {
        TokenType[] expectedArray = {TokenType.EOF, TokenType.TESTCASE, TokenType.AT};
        TokenType[] actualArray = {TokenType.EOF, TokenType.EOL, TokenType.TEST_SUITE};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    private static void testPrivateStaticAssertObjectArray() {
        TokenType[] expectedArray = {TokenType.EOF, TokenType.TESTCASE, TokenType.AT};
        TokenType[] actualArray = {TokenType.EOF, TokenType.EOL, TokenType.TEST_SUITE};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testThrowsForArrayFloat() {
        double[] expectedArray = {1.234, 1.237, 1.239};
        double[] actualArray = {1.235, 1.233, 1.236};
        Asserts.assertArrayEquals(expectedArray, actualArray, 0.05);
    }
}
