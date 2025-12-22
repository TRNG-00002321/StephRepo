package com.revature.ra;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("REST Assured Basic Demo")
public class TestRestAssured01 {

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterAll
    static void tearDown(){
        RestAssured.reset();
    }

    @Test
    @DisplayName("First REST Assured test - GET a post")
    public void firstTest_getPost_returnsSuccess(){
        given()
                .log().all()
        .when()
                .get("/posts/1")
        .then()
                .log().all()
                .statusCode(200);
    }
    @Test
    @DisplayName("Detailed Given-When-Then example")
    void givenWhenThen_detailed_breakdown() {
        given()
                .log().parameters()
                .header("Accept","application/json")
                .queryParam("userId",1)
        .when()
                .get("/posts")
       .then()

                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()",greaterThan(0));
    }

    @DisplayName("Testing Other Things")
    @Test
    public void test_others(){
        given()
                .filter(new AllureRestAssured())
                .when()
                .get("/users/1")
                .then()
                .statusCode(200)
                .body("name", equalTo("Leanne Graham"))
                .body("email", containsString("@"))
                // Nested object access
                .body("address.city", equalTo("Gwenborough"))
                .body("address.geo.lat", notNullValue())
                // Company nested object
                .body("company.name", equalTo("Romaguera-Crona"));
    }
}
