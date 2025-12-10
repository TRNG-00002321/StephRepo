package com.revature.demo;

import com.revature.unit.UserValidation;
import com.revature.unit.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTesting
{
    UserValidation validator;
    @BeforeEach
    public void setUp()
    {
        validator = new UserValidation();
    }

    @Test
    void validateEmail_MultiInvalidInputs_ThrowException()
    {
        assertAll("Email validation exceptions",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateEmail(null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateEmail("")),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> validator.validateEmail("invalid"))
        );
    }

    @Test
    void validateEmail_NullInput_hasCorrectMessage() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail(null)
        );
        assertTrue(ex.getMessage().contains("Email cannot be null"));
    }

    @Test
    void validateEmail_EmptyInput_hasCorrectMessage() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail("")
        );
        assertTrue(ex.getMessage().contains("Email cannot be empty"));
    }

    @Test
    void validateEmail_NoAtSignInput_hasCorrectMessage() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail("noatsignhere")
        );
        assertTrue(ex.getMessage().contains("Email must contain @"));
    }

    @Test
    void validateEmail_NoDomainInput_hasCorrectMessage() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateEmail("missing.domain@")
        );
        assertTrue(ex.getMessage().contains("Email has invalid format"));
    }

    @Test
    void validateEmail_ValidInput_hasCorrectMessage() {
        assertDoesNotThrow(() -> validator.validateEmail("billybod@email"));

    }

    @Test
    void validatePassword_NullInput_hasCorrectMessage() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword(null)
        );
        assertTrue(ex.getMessage().contains("Password cannot be null"));
    }

    @Test
    void validatePassword_ShortInput_hasCorrectMessage() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword("short")
        );
        assertTrue(ex.getMessage().contains("Password must be at least 8 characters"));
    }

    @Test
    void validatePassword_noUpperCaseInput_hasCorrectMessage() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword("nouppercase")
        );
        assertTrue(ex.getMessage().contains("Password must contain an uppercase letter"));
    }

    @Test
    void validatePassword_noLowerCaseInput_hasCorrectMessage() {
        ValidationException ex = assertThrows(
                ValidationException.class,
                () -> validator.validatePassword("NOLOWERCASE")
        );
        assertTrue(ex.getMessage().contains("Password must contain a lowercase letter"));
    }

    @Test
    void validateAge_NegativeInput_hasCorrectMessage() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateAge(-20)
        );
        assertTrue(ex.getMessage().contains("Age cannot be negative"));
    }

    @Test
    void validateAge_OverAgeLimitInput_hasCorrectMessage() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateAge(168)
        );
        assertTrue(ex.getMessage().contains("Age cannot exceed 150"));
    }

    @Test
    void validateAge_ValidInput_hasCorrectMessage() {
        assertDoesNotThrow(() -> validator.validateAge(56));

    }



}
