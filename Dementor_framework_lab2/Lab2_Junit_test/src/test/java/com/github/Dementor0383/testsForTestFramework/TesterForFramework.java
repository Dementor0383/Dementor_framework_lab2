package com.github.Dementor0383.testsForTestFramework;

import com.github.Dementor0383.Asserts;
import com.github.Dementor0383.TestRunner;
import com.github.Dementor0383.myException.FrameworkException;
import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class TesterForFramework {

    @Test(expected = FrameworkException.class)
    public void testThrowsForDifferentObject() {
        String actualLine = " line";
        String expectedLine = "Line";
        Asserts.assertEquals(expectedLine, actualLine);
    }

    @Test(expected = FrameworkException.class)
    public void testFailArrayAssert() {
        int[] actualArray = new int[3];
        int[] expectedArray = new int[3];
        // CR: int[] actualArray = {1, 2, 3}
        actualArray[0] = 1;
        actualArray[1] = 2;
        actualArray[2] = 3;
        expectedArray[0] = 4;
        expectedArray[1] = 2;
        expectedArray[2] = 3;
        Asserts.assertArrayEquals(expectedArray, actualArray);
    }

    @Test(expected = FrameworkException.class)
    public void testArrayCharAssert() {
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
    public void testOnlyPublicBeforeCalled() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File testDataDir = new File("testdata");
        File fileToLoad = new File("testdata/" + "PrivateBefore" + ".java");

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(null, null, null, fileToLoad.toString());

        // CR: all class initailization can be moved to utils method
        URLClassLoader classLoader = new URLClassLoader(new URL[]{testDataDir.toURI().toURL()});
        Class<?> privateBefore = Class.forName("PrivateBefore", true, classLoader);

        TestRunner.testRunner(privateBefore);
    }

    @Test
    public void testOnlyPublicTestCalled() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File testDataDir = new File("testdata");
        File fileToLoad = new File("testdata/" + "PrivateTest" + ".java");

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(null, null, null, fileToLoad.toString());

        URLClassLoader classLoader = new URLClassLoader(new URL[]{testDataDir.toURI().toURL()});
        Class<?> privateTest = Class.forName("PrivateTest", true, classLoader);

        // CR: and how do you determine that your test wasn't invoked?
        TestRunner.testRunner(privateTest);
    }

    @Test
    public void testOnlyPublicAfterCalled() throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File testDataDir = new File("testdata");
        File fileToLoad = new File("testdata/" + "PrivateAfter" + ".java");

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        javaCompiler.run(null, null, null, fileToLoad.toString());

        URLClassLoader classLoader = new URLClassLoader(new URL[]{testDataDir.toURI().toURL()});
        Class<?> privateAfter = Class.forName("PrivateAfter", true, classLoader);

        TestRunner.testRunner(privateAfter);
    }


}
