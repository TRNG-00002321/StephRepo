package com.revature.demo;

import com.revature.unit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MultTest
{
    Calculator c= null;

    @Test
    @DisplayName("Testing Multiply Methods... Positive")
    void testMult(){
        //Arrange
        c = new Calculator();
        int n = 4;
        int n2 = 5;
        int eResult = 20;
        int result;

        //Act
        result = c.mult(n,n2);
        System.out.println("Test Multiply Positive");

        //Assert
        Assertions.assertEquals(eResult,result);

    }

    @Test
    @DisplayName("Testing Multiply Methods... Negative")
    @Disabled
    void testMultNegative(){
        //Arrange
        int n = 10;
        int n2 = 12;
        int eResult = 121;
        int result;

        //Act
        result = c.mult(n,n2);
        System.out.println("Test Mult Negative");
        //Assert
        Assertions.assertEquals(eResult,result);

    }

}
