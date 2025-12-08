package com.revature.demo;

import com.revature.unit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SubTest
{
    Calculator c = null;
    @Test
    @DisplayName("Testing Subtract Methods... Positive")
    void testSub(){
        //Arrange
        c = new Calculator();
        int n = 12;
        int n2 = 10;
        int eResult = 2;
        int result;

        //Act
        result = c.sub(n,n2);
        System.out.println("Test Subtract Positive");

        //Assert
        Assertions.assertEquals(eResult,result);

    }


    @Test
    @DisplayName("Testing Subtract Methods... Negative")
    void testSubNegative(){
        //Arrange
        c = new Calculator();
        int n = 12;
        int n2 = 10;
        int eResult = 3;
        int result;

        //Act
        result = c.sub(n,n2);
        System.out.println("Test Subtract Negative");

        //Assert
        Assertions.assertEquals(eResult,result);

    }
}
