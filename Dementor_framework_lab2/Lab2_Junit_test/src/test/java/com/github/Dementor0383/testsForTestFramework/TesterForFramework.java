package com.github.Dementor0383.testsForTestFramework;

import com.github.Dementor0383.Asserts;
import com.github.Dementor0383.TestRunner;
import com.github.Dementor0383.myException.FrameworkException;
import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TesterForFramework {

    private Class<?> testInitialization(String testName, Path testDataDir, Path testLoadFile) throws ClassNotFoundException, MalformedURLException {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(null, null, null, testLoadFile.toString());

        URLClassLoader classLoader = new URLClassLoader(new URL[]{testDataDir.toUri().toURL()});
        return Class.forName(testName, true, classLoader);
    }

    @Test(expected = FrameworkException.class)
    public void testThrowsForDifferentObject() {
        String actualLine = " line";
        String expectedLine = "Line";
        Asserts.assertEquals(expectedLine, actualLine);
    }

    @Test
    public void testThrowsForDifferentFloat() {
        float actual = (float) 2.139;
        float expected = (float) 2.128;
        Asserts.assertEquals(expected, actual, 0.5F);
    }

    @Test(expected = FrameworkException.class)
    public void testThrowsForDifferentFloatTwo() {
        float actual = (float) 2.139;
        float expected = (float) 2.128;
        Asserts.assertEquals(expected, actual, 0.01F);
    }

    @Test
    public void testThrowsForDifferentDouble() {
        double actual = 2.139;
        double expected = 2.128;
        Asserts.assertEquals(expected, actual, 0.5);
    }

    @Test(expected = FrameworkException.class)
    public void testThrowsForDifferentDoubleTwo() {
        double actual = 2.139;
        double expected = 2.128;
        Asserts.assertEquals(expected, actual, 0.01);
    }

    @Test(expected = FrameworkException.class)
    public void testThrowsForDifferentDoubleWithoutErrorRate() {
        double actual = 2.139;
        double expected = 2.128;
        Asserts.assertEquals(expected, actual);
    }

    @Test(expected = FrameworkException.class)
    public void testThrowsForArrayObject() {
        TokenType[] expectedArray = {TokenType.EOF, TokenType.TEST_SUITE, TokenType.EOL};
        TokenType[] actualArray = {TokenType.EOF, TokenType.EOL, TokenType.EOL};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testThrowsForArrayFloat() {
        double[] expectedArray = {1.234, 1.237, 1.239};
        double[] actualArray = {1.235, 1.233, 1.236};
        Asserts.assertArrayEquals(expectedArray, actualArray, 0.05);
    }

    @Test(expected = FrameworkException.class)
    public void testThrowsForObject() {
        TokenType expected = TokenType.EOF;
        TokenType actual = TokenType.EOL;
        Asserts.assertEquals(expected, actual);
    }

    @Test
    public void testThrowsForObjectTwo() {
        TokenType expected = TokenType.TEST_SUITE;
        TokenType actual = TokenType.TEST_SUITE;
        Asserts.assertEquals(expected, actual);
    }

    @Test(expected = FrameworkException.class)
    public void testFailArrayAssert() {
        int[] actualArray = {1, 2, 3};
        int[] expectedArray = {4, 2, 3};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test(expected = FrameworkException.class)
    public void testArrayCharAssert() {
        char[] actualArray = {'!', 'g', 'h'};
        char[] expectedArray = {'!', 'g', '?'};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testArrayAssert() {
        int[] actualArray = {1, 2, 3};
        int[] expectedArray = {1, 2, 3};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test(expected = FrameworkException.class)
    public void testStringArrayAssert() {
        String[] actualArray = {"Test1", "Test2", "Test3"};
        String[] expectedArray = {"Test1", "Test4", "Test3"};
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testOnlyPublicBeforeCalled() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Path testDataDir = Paths.get("testdata");
        Path fileToLoad = Paths.get("testdata/" + "PrivateBefore" + ".java");

        Class<?> privateBefore = testInitialization("PrivateBefore", testDataDir, fileToLoad);
        TestRunner.runTests(privateBefore);
    }

    @Test
    public void testOnlyPublicTestCalled() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Path testDataDir = Paths.get("testdata");
        Path fileToLoad = Paths.get("testdata/" + "PrivateTest" + ".java");

        Class<?> privateTest = testInitialization("PrivateTest", testDataDir, fileToLoad);
        TestRunner.runTests(privateTest);
    }

    @Test
    public void testOnlyPublicAfterCalled() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Path testDataDir = Paths.get("testdata");
        Path fileToLoad = Paths.get("testdata/" + "PrivateAfter" + ".java");

        Class<?> privateAfter = testInitialization("PrivateAfter", testDataDir, fileToLoad);
        TestRunner.runTests(privateAfter);
    }

    @Test
    public void testOnlyWithoutArgumentsTest() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Path testDataDir = Paths.get("testdata");
        Path fileToLoad = Paths.get("testdata/" + "TestWithArguments" + ".java");

        Class<?> withArguments = testInitialization("TestWithArguments", testDataDir, fileToLoad);
        TestRunner.runTests(withArguments);
    }

    @Test
    public void testPrivateStatic() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Path testDataDir = Paths.get("testdata");
        Path fileToLoad = Paths.get("testdata/" + "RightTest" + ".java");

        Class<?> rightTest = testInitialization("RightTest", testDataDir, fileToLoad);
        TestRunner.runTests(rightTest);
    }

    @Test
    public void testPrivateFinal() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Path testDataDir = Paths.get("testdata");
        Path fileToLoad = Paths.get("testdata/" + "PrivateFinalTest" + ".java");

        Class<?> privateFinalTest = testInitialization("PrivateFinalTest", testDataDir, fileToLoad);
        TestRunner.runTests(privateFinalTest);
    }

}
