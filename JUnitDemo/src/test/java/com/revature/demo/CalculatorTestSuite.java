package com.revature.demo;

import org.junit.platform.suite.api.*;

// Marks the class as a test suite
@Suite
// Sets a display name for the suite in reports
@SuiteDisplayName("Calculator Test Suite")
// Selects the specific classes to include in this suite
@SelectClasses({
        AddTest.class,
        SubTest.class,
        MultTest.class,
        DivTest.class,
        CalcParamTest.class,
        StringUtilsTest.class,
        ExceptionTesting.class
})
public class CalculatorTestSuite {
    // This class remains empty; it is purely a container for the annotations
}
