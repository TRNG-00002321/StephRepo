package com.revature.demo;

import com.revature.unit.StringUtils;
import com.revature.unit.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;

import static com.revature.unit.StringUtils.*;
import static org.junit.jupiter.api.Assertions.*;

/*
Assignment :
Task 1: Test reverse()
Task 2: Test isEmpty()
Task 3: Test findFirst() with Null Handling
Task 4: Test split() with Array Assertions
Task 5: Master assertAll()
Write a single test that validates a User object using assertAll:
*/
public class StringUtilsTest
{
    @Test
    public void Reverse_Tests()
    {
        //Asserts(expected value, Action(Arrange))
        //nums
        assertEquals("6,5,4,3,2,1",reverse("1,2,3,4,5,6"), "numbers are reversed");
        //words
        assertEquals("esarhp a si siht", reverse("this is a phrase"), "words are reversed");
        //empty
        assertEquals("",reverse(""), "empty fields handled");
        //null
        assertNull(reverse(null), "null fields handled");
    }

    @Test
    public void isEmpty_Tests()
    {
        //Asserts
        assertTrue(isEmpty(""));
        assertTrue(isEmpty(null), "null field handled");
        assertFalse(isEmpty("   "),"Spaces count as characters so not empty");
        assertFalse(isEmpty(" hello "));
    }

    @Test
    public void findFirst_Null_Handling()
    {
        //Arrange
        String[] arr = {"Avacado", "Plum", "Apple", "Grapes", "Guava", "Raspberry"};

        //Assert
        assertNull(findFirst(arr,"a"), "Case sensitive so no value is returned");
        assertNotNull(findFirst(arr,"P"));
    }

    @Test
    public void split_Array_Assertions()
    {
        //Arrange
        String arr = "Avacado,Plum,Apple,Grapes,Guava,Raspberry";

        //Assert
        assertArrayEquals(
                new String[]{"Avacado", "Plum", "Apple", "Grapes", "Guava", "Raspberry"},
                split(arr,","));

    }

    @Test
    void user_allPropertiesValid() {
        User user = StringUtils.parseUser("1,steef,tacocat,21");

        assertAll("User properties",
                () -> assertEquals("steef", user.getUsername()),
                () -> assertEquals("tacocat", user.getPassword()),
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals(21,user.getAge())
        );
    }












}
