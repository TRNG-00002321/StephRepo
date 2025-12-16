package com.revature.ra;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class DemoRestCRUD 
{
    private static RequestSpecification requestSpec;

    private static int createdPostId;

    @BeforeAll
    static void setupAll() {
        // One-time setup for all tests
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "";

        requestSpec = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + getAuthToken());

        createdPostId = -1;
    }

    @BeforeEach
    void setupEach(TestInfo testInfo) {
        System.out.println("Running: " + testInfo.getDisplayName());
    }

    @AfterEach
    void teardownEach() {
        // Cleanup after each test if needed
    }

    @AfterAll
    static void teardownAll() {
        RestAssured.reset();
    }

    @Test
    @Order(1)
    @DisplayName("CREATE - POST new post")
    void create_post_returnsCreatedResource() {
        // Request body as JSON string
        String requestBody = """
            {
                "title": "Test Post from REST Assured",
                "body": "This post was created during our demo",
                "userId": 1
            }
            """;

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)  // Created
                .body("title", equalTo("Test Post from REST Assured"))
                .body("body", containsString("demo"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract()
                .response();

        // Store ID for later tests
        createdPostId = response.jsonPath().getInt("id");
        System.out.println("Created post with ID: " + createdPostId);
    }

    @Test
    @Order(2)
    @DisplayName("CREATE - POST with Java Object")
    public void testSerialObject(){
        //Class on the fly (simple inner class)
        //record Post(String title, String body, int userId){}

        Post nPost = new Post("POJO Test","Testing a POJO Object",1);

        Response response = given()
                .spec(requestSpec)
                .body(nPost)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)  // Created
                .body("title", equalTo("POJO Test"))
                .body("body", containsString("Testing a POJO Object"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract()
                .response();

        // Store ID for later tests
        createdPostId = response.jsonPath().getInt("id");
        System.out.println("Created post with ID: " + createdPostId);
    }

    @Test
    @Order(3)
    @DisplayName("PUT - POST with Java Object")
    public void put_post_returnsUpdatedResource(){
        //Class on the fly (simple inner class)
        //record Post(String title, String body, int userId){}

        Post nPost = new Post("POJO Test","Testing Update to POJO Object",1);

        Response response = given()
                .spec(requestSpec)
                .body(nPost)
                .when()
                .put("/posts/1")
                .then()
                .statusCode(200)
                .body("body", containsString("Testing Update to POJO Object"))
                .body("userId", equalTo(1))
                .extract().response();

        // Store ID for later tests
        createdPostId = response.jsonPath().getInt("id");
        System.out.println("Updated post with ID: " + createdPostId);
    }

    @Test
    @DisplayName("DELETE - remove post")
    void delete_post_returnsSuccess() {

        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/1")
                .then()
                .statusCode(anyOf(is(200), is(204)));
    }



    @ParameterizedTest(name = "GET /posts/{0} returns 200")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("GET multiple posts by ID")
    void getPost_variousIds_return200(int postId) {
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .body("id", is(postId))
                .log().all();


    }




    @ParameterizedTest(name = "User {0} has name {1}")
    @CsvSource({
            "1, Leanne Graham",
            "2, Ervin Howell",
            "3, Clementine Bauch",
            "4, Patricia Lebsack",
            "5, Chelsey Dietrich"
    })
    @DisplayName("Validate user names")
    void getUser_validateName_matchesExpected(int userId, String expectedName) {
        given()
                .spec(requestSpec)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo(expectedName.trim()));
    }


    @Test
    @Order(13)
    @DisplayName("Complete CRUD flow in single test")
    void completeCrudFlow_createReadUpdateDelete() {

        // CREATE
        String createBody = """
            {
                "title": "CRUD Test Post",
                "body": "Testing complete CRUD flow",
                "userId": 1
            }
            """;

        int newId = given()
                .spec(requestSpec)
                .body(createBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("CRUD Test Post"))
                .extract()
                .path("id");

        System.out.println("Created ID: " + newId);

        // READ
        given()
                .spec(requestSpec)
                .when()
                .get("/posts/" + 1)
                .then()
                .statusCode(200)
                .body("id", equalTo(1));

        // UPDATE
        String updateBody = """
            {
                "id": %d,
                "title": "Updated CRUD Test",
                "body": "Body was updated",
                "userId": 1
            }
            """.formatted(newId);

        given()
                .spec(requestSpec)
                .body(updateBody)
                .when()
                .put("/posts/" + 1)
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated CRUD Test"));

        // DELETE
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/" + 1)
                .then()
                .statusCode(200);

        System.out.println("CRUD flow completed successfully!");
    }

    @Test
    @DisplayName("Extract and assert with JUnit")
    void extractAndAssert_withJUnit() {
        var response = given()
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        // JUnit assertions
        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode, "Status should be 200");

        int userCount = response.jsonPath().getList("$").size();
        Assertions.assertEquals(10, userCount, "Should have 10 users");

        String firstUserName = response.jsonPath().getString("[0].name");
        Assertions.assertNotNull(firstUserName, "First user should have a name");
        Assertions.assertFalse(firstUserName.isEmpty(), "Name should not be empty");
    }
    private static String getAuthToken() {
        return "test-token";
    }









}
