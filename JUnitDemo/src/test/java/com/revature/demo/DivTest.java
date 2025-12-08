package com.revature.demo;

import com.revature.unit.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DivTest
{
    Calculator c = null;

    @Test
    @DisplayName("Testing Divide Methods... Positive")
    void testDiv(){
        //Arrange
        c = new Calculator();
        int n = 12;
        int n2 = 2;
        int eResult = 6;
        int result;

        //Act
        result = c.div(n,n2);
        System.out.println("Test Divide Positive");

        //Assert
        Assertions.assertEquals(eResult,result);

    }


    @Test
    @DisplayName("Testing Divide Methods... Negative")
    void testDivNegative(){
        //Arrange
        c = new Calculator();
        int n = 12;
        int n2 = 0;

        //Act
        System.out.println("Test Divide Negative");

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            int result = c.div(n,n2);
        });

    }


}
