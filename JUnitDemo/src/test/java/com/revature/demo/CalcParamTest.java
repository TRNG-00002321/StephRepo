package com.revature.demo;

import com.revature.unit.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalcParamTest
{
    Calculator c = null;

    @BeforeEach
    public void setup(){
        c = new Calculator();
    }

    @ParameterizedTest(name="{0} +{1} = {2}")
    @CsvSource({
            "1, 2, 3",
            "2,2,4",
            "3,2,4",
            "4,4,8"
    })
    @Order(1)
    public void testAdd(int a, int b, int eResult){
        Assertions.assertEquals(eResult,c.add(a,b));

    }

    @ParameterizedTest(name="Adding {0} and {1} is {2}")
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    @Order(2)
    public void testAddAgain(int a, int b, int eResult){
        Assertions.assertEquals(eResult,c.add(a,b));

    }

    @ParameterizedTest(name="Adding {0} and {1} is {2} using method")
    @MethodSource("testDataMethod")
    @Order(3)
    public void testAddThird(int a, int b, int eResult){
        Assertions.assertEquals(eResult,c.add(a,b));

    }
    static Stream<Arguments> testDataMethod(){
        return Stream.of(Arguments.of(1,2,3), Arguments.of(-2,4,2));
    }
}
