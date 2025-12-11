package com.revature.demo;

import com.revature.unit.MockDatabase;
import com.revature.unit.UserRepository;
import com.revature.unit.UserSimple;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserRepositoryTest {

    private static MockDatabase database;
    private UserRepository repository;

    @BeforeAll
    static void setUpDatabase() {
        // TODO: Initialize the database connection
        // This runs ONCE before all tests
        //System.out.println("Connecting to database...");
        System.out.println("1. @BeforeAll: Setting up database");
        database = new MockDatabase();
        database.connect();
    }
    @BeforeEach
    void setUpTest() {
        // TODO: Clear all data from database
        // TODO: Create a new repository instance
        // TODO: Insert any test fixtures needed
        database.clearAll();
        repository = new UserRepository(database);

        // Optional: Insert baseline test data
        database.insert(new UserSimple(1, "Admin", "admin@test.com"));
        System.out.println("  2. @BeforeEach: Preparing Test");

    }

    @Test
    @DisplayName("Test 1: Add user and verify")
    void test1_addUser() {
        // Add a user
        repository.save(new UserSimple(2, "John", "john@test.com"));

        // Verify it exists
        assertEquals(2, repository.count());  // Admin + John
        System.out.println("    3. @Test: Running Test");

    }

    @Test
    @DisplayName("Test 2: Should have fresh state")
    void test2_freshState() {
        // This test should ONLY see the Admin user
        // NOT the John user from test1
        assertEquals(1, repository.count());  // Only Admin
        System.out.println("    3. @Test: Running Test");
    }

    @Test
    @DisplayName("Test 3: Database operations work independently")
    void test3_independentOperations() {
        repository.save(new UserSimple(3, "Jane", "jane@test.com"));
        repository.save(new UserSimple(4, "Bob", "bob@test.com"));

        // Should have Admin + 2 new users
        assertEquals(3, repository.count());
        System.out.println("    3. @Test: Running Test");
    }

    @AfterEach
    void tearDownTest() {
        // TODO: Any per-test cleanup
        // Note: The database is cleared in setUpTest anyway
        //System.out.println("Test completed, data will be reset");
        System.out.println("  4. @AfterEach: Cleaning up test");
    }

    @AfterAll
    static void tearDownDatabase() {
        // TODO: Close the database connection
        // This runs ONCE after all tests
        //System.out.println("Disconnecting from database...");
        database.disconnect();
        System.out.println("5. @AfterAll: Closing database");
    }
}