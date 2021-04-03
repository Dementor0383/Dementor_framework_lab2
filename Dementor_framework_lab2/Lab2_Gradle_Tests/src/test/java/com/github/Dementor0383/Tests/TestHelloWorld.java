package com.github.Dementor0383.Tests;

import com.github.Dementor0383.Asserts;
import com.github.Dementor0383.annotation.Test;

public class TestHelloWorld {
    public String createStringLine(String line){
        String currentLine = line;
        return "Current line is <" + currentLine + ">!";
    }

    @Test
    public void testHelloWorld(){
        String actualLine = createStringLine("Test Line");
        String expectedLine = "Current line is <Test Line>!";
        Asserts.assertEquals(expectedLine, actualLine);
    }
}
