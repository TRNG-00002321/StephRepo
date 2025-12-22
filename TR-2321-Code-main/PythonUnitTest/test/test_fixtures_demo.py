import pytest
from src.calculator import Calculator

@pytest.fixture
def calculator():
    """
    Provide a Calculator instance for tests.
    Any test that has 'calculator' as a parameter gets this fixture injected.
    """
    return Calculator()

def test_add_with_fixture(calculator):
    result = calculator.add(1, 2)
    assert result == 3

@pytest.fixture
def database_connection():
    # Setup: Establish a database connection
    print("Establishing database connection...")
    connection = "simulated_db_connection" # Replace with actual connection logic
    yield connection  # Yield the connection to the test
    # Teardown: Close the database connection
    print("Closing database connection...")
    # connection.close() # Replace with actual close logic

def test_database_operation(database_connection):
    print(f"Using database connection: {database_connection}")
    # Perform database operations using the connection
    assert database_connection == "simulated_db_connection"

# content of test_example.py
def test_user_name(sample_data):
    """Test the 'name' key from the sample_data fixture."""
    assert sample_data["name"] == "Alice"

def test_user_age(sample_data):
    """Test the 'age' key from the sample_data fixture."""
    assert sample_data["age"] == 30