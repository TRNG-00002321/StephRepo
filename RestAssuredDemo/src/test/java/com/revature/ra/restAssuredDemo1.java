package com.revature.ra;

import io.restassured.RestAssured;
import io.restassured.http.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;


public class restAssuredDemo1
{
    @BeforeAll
    static void setUp(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";

    }
    @AfterAll
    static void tearDown(){
        RestAssured.reset();

    }
    @Test
    public void firstRequestDemo()
    {

        given()
                .log().all()
                .when().get("/posts/1")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testWithMoreDetails()
    {
        given()
                .log().parameters()
                .queryParam("userId", 1)
                .when()
                .get("/posts")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", Matchers.greaterThan(0));
    }

    @Test
    public void testUserDetails()
    {
        given()
                .when()
                .get("users/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", Matchers.equalTo("Leanne Graham"));
    }

}
