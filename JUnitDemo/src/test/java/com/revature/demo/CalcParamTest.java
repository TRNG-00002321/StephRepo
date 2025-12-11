package com.revature.demo;

import com.revature.unit.Calculator;
import com.revature.unit.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalcParamTest {

    private Calculator c;

    @BeforeEach
    public void setup() {
        c = new Calculator();
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1,   2,   3",
            "0,   0,   0",
            "-1,  1,   0",
            "100, 200, 300"
    })
    @Order(1)
    public void testAdd(int a, int b, int eResult) {
        Assertions.assertEquals(eResult, c.add(a, b));
    }

    @ParameterizedTest(name = "{0} - {1} = {2}")
    @CsvSource({
            "1,    2,   -1",
            "0,    0,    0",
            "5,    1,    4",
            "1000, 200,  800"
    })
    @Order(2)
    public void testSubtract(int a, int b, int eResult) {
        Assertions.assertEquals(eResult, c.sub(a, b));
    }

    @ParameterizedTest(name = "{0} * {1} = {2}")
    @CsvSource({
            "1,    2,    2",
            "0,    0,    0",
            "-1,   1,   -1",
            "100,  200, 20000",
            "-5,  -4,   20"
    })
    @Order(3)
    public void testMultiply(int a, int b, int eResult) {
        Assertions.assertEquals(eResult, c.mult(a, b));
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "1,    2,    0",
            "-1,   1,   -1",
            "200,  100,  2",
            "-20, -4,    5"
    })
    @Order(4)
    public void testDivide(int a, int b, int eResult) {
        Assertions.assertEquals(eResult, c.div(a, b));
    }


    @ParameterizedTest(name = "Adding {0} and {1} = {2}")
    @CsvFileSource(resources = "/testData.csv", numLinesToSkip = 1)
    @Order(5)
    public void testAddAgain(int a, int b, int eResult) {
        Assertions.assertEquals(eResult, c.add(a, b));
    }


    @ParameterizedTest(name = "Adding {0} and {1} = {2} (method source)")
    @MethodSource("testDataMethod")
    @Order(6)
    public void testAddThird(int a, int b, int eResult) {
        Assertions.assertEquals(eResult, c.add(a, b));
    }

    static Stream<Arguments> testDataMethod() {
        return Stream.of(
                Arguments.of(1,   2,  3),
                Arguments.of(-2,  4,  2),
                Arguments.of(10,  2, 12),
                Arguments.of(9,   3, 12),
                Arguments.of(-10, 2, -8),
                Arguments.of(7,   2,  9)
        );
    }

    @ParameterizedTest(name = "{0} is Even")
    @ValueSource(ints = {2, 4, 6, 100, 0, -2})
    @Order(7)
    public void isEven_evenNumbers_returnsTrue(int number) {
        Assertions.assertTrue(c.isEven(number));
    }

    @ParameterizedTest(name = "\"{0}\" is Blank")
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n"})
    @Order(8)
    public void isBlank_blankInputs_returnsTrue(String input) {
        Assertions.assertTrue(StringUtils.isBlank(input));
    }
}
