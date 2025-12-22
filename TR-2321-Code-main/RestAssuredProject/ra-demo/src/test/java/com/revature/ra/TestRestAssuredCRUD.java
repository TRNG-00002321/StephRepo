package com.revature.ra;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("REST Assured CRUD Operations Demo")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRestAssuredCRUD {
    // Shared specifications for reusability
    private static RequestSpecification requestSpec;
    private static ResponseSpecification successResponseSpec;

    // Store created resource ID for chaining
    private static int createdPostId;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .addHeader("X-Custom-Header", "RestAssuredDemo")
                .build();

       successResponseSpec = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectResponseTime(lessThan(5000L))
                .build();
    }
    // SECTION 1: CREATE (POST)
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
    @DisplayName("CREATE - POST with Java object (POJO)")
    void create_postWithPojo_serializesAutomatically() {
       // Simple inner class for demo (normally in separate file)
        record Post(String title, String body, int userId) {}

        Post newPost = new Post(
                "POJO Test Post",
                "Created using a Java record",
                2
        );

        given()
                .spec(requestSpec)
                .body(newPost)  // REST Assured serializes to JSON
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("title", equalTo("POJO Test Post"))
                .body("userId", equalTo(2));
    }

    // SECTION 2: READ (GET)

    @Test
    @Order(3)
    @DisplayName("READ - GET single resource")
    void read_singlePost_returnsPost() {
                given()
                .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .spec(successResponseSpec)
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue());
    }

    // SECTION 9: Complete CRUD Flow

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
                .get("/posts/" + newId)
                .then()
                .statusCode(200)
                .body("id", equalTo(newId));

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
                .put("/posts/" + newId)
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated CRUD Test"));

        // DELETE
        given()
                .spec(requestSpec)
                .when()
                .delete("/posts/" + newId)
                .then()
                .statusCode(200);

        System.out.println("CRUD flow completed successfully!");
    }

}
