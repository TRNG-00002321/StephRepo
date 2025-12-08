package com.revature.demo;

import com.revature.unit.Calculator;
import org.junit.jupiter.api.*;
import org.junit.platform.suite.api.Suite;


public class AddTest
{
    Calculator c = null;
    @Test
    @DisplayName("Testing Add Methods... Positive")
    void testAdd(){
        //Arrange
        c = new Calculator();
        int n = 10;
        int n2 = 12;
        int eResult = 22;
        int result;

        //Act
         result = c.add(n,n2);
        System.out.println("Test Add Positive");

        //Assert
        Assertions.assertEquals(eResult,result);

    }

    @Test
    @DisplayName("Testing Add Methods... Negative")
    @Disabled
    void testAddNegative(){
        //Arrange
        int n = 10;
        int n2 = 12;
        int eResult = 22;
        int result;

        //Act
        result = c.add(n,n2);
        System.out.println("Test Add Negative");
        //Assert
        Assertions.assertEquals(eResult,result);

    }





    @BeforeEach
    public void setUp(){
        System.out.println("this  method is called before each method.....beforeEach");
    }

    @AfterEach
    public void tearDown(){
        System.out.println("This is the tear down method ...... AfterEach");
    }

    @BeforeAll
    public static void setupClass(){
        System.out.println("Before All method is called.");
    }

    @AfterAll
    public static void teardownClass(){
        System.out.println("After All method is called.");
    }
}
