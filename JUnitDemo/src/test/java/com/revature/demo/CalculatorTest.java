package com.revature.demo;

import org.junit.jupiter.api.*;
import com.revature.unit.Calculator;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest
{
    Calculator c = null;

    @BeforeEach
    void setUp()
    {
        c = new Calculator();
    }
    @Test
    @DisplayName("Testing Add Two Positive Inputs")
    void testAdd_TwoPosInputs(){
        //Arrange

        int n = 5;
        int n2 = 3;
        int eResult = 8;
        int result;

        //Act
        result = c.add(n,n2);
        System.out.println("Test Add Positive");

        //Assert
        Assertions.assertEquals(eResult,result);

    }

    @Test
    @DisplayName("Testing Add One Positive and One Negative Input")
    void testAdd_OnePosInputs(){
        //Arrange

        int n = 10;
        int n2 = -3;
        int eResult = 7;
        int result;

        //Act
        result = c.add(n,n2);
        System.out.println("Test Add Positive");

        //Assert
        Assertions.assertEquals(eResult,result);

    }

    @Test
    @DisplayName("Testing Add Two Negative Inputs")
    void testAdd_TwoNegInputs(){
        //Arrange

        int n = -5;
        int n2 = -3;
        int eResult = -8;
        int result;

        //Act
        result = c.add(n,n2);
        System.out.println("Test Add Positive");

        //Assert
        Assertions.assertEquals(eResult,result);

    }

    @Test
    @DisplayName("Testing Add Number Zero Input")
    void testAdd_ZeroInput(){
        //Arrange
        int n = 42;
        int n2 = 0;
        int eResult = 42;
        int result;

        //Act
        result = c.add(n,n2);
        System.out.println("Test Add Negative");
        //Assert
        Assertions.assertEquals(eResult,result);

    }


    //Subtraction


    @Test
    @DisplayName("Testing Subtract Basics")
    void testSub_Basic(){
        //Arrange

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
    @DisplayName("Testing Subtract into Negatives")
    void testSub_NegativeOutput(){
        //Arrange

        int n = 8;
        int n2 = 10;
        int eResult = -2;
        int result;

        //Act
        result = c.sub(n,n2);

        //Assert
        Assertions.assertEquals(eResult,result);

    }

    @Test
    @DisplayName("Testing Subtraction of Zero")
    void testSub_ZeroInput(){
        //Arrange

        int n = 12;
        int n2 = 0;
        int eResult = 12;
        int result;

        //Act
        result = c.sub(n,n2);

        //Assert
        Assertions.assertEquals(eResult,result);
    }

    @Test
    @DisplayName("Testing Subtract Methods... Negative")
    void testSubNegative(){
        //Arrange

        int n = 12;
        int n2 = 10;
        int eResult = 3;
        int result;

        //Act
        result = c.sub(n,n2);
        System.out.println("Test Subtract Negative");

        //Assert
        Assertions.assertNotEquals(eResult,result);
    }


    //Is Even

    @Test
    @DisplayName("Testing Even method with Positive Input")
    void isEven_EvenInput(){
        //Arrange
        int n = 4;
        boolean result;

        //Act
        result = c.isEven(n);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Testing Even method with Odd Input")
    void isEven_OddInput(){
        //Arrange
        int n = 7;
        boolean result;

        //Act
        result = c.isEven(n);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Testing Even method with Number Zero Input")
    void isEven_ZeroInput(){
        //Arrange
        int n = 0;
        boolean result;

        //Act
        result = c.isEven(n);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Testing Even method with Negative Input")
    void isEven_NegInput(){
        //Arrange
        int n = -8;
        boolean result;

        //Act
        result = c.isEven(n);

        //Assert
        assertTrue(result);
    }


    //Is Positive

    @Test
    @DisplayName("Testing Positive method with Positive Input")
    void isPositive_PosInput(){
        //Arrange
        int n = 4;
        boolean result;

        //Act
        result = c.isPositive(n);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Testing Positive method with Negative Input")
    void isPositive_NegInput(){
        //Arrange
        int n = -9;
        boolean result;

        //Act
        result = c.isPositive(n);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Testing Positive method with Number Zero Input")
    void isPositive_ZeroInput(){
        //Arrange
        int n = 0;
        boolean result;
        //Act
        result = c.isPositive(n);

        //Assert
        assertFalse(result);
    }


    /*
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

     */
}
