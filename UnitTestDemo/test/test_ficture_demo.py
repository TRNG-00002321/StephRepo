import pytest


#pytest .\test\test_ficture_demo.py -v
@pytest.fixture
def db_connection():
    #set up portion / before each method
    print("Connecting to database")
    connection = "simulated_DB_connection"

    #Fancy test stuff in the middle happens on execution

    yield connection
    #tear down portion /after each method
    print("Disconnecting from database")

def test_db_operation(db_connection):
    print(f"Using database connection {db_connection}")

    assert db_connection == "simulated_DB_connection"


def test_conftest_user(sample_data):
    assert sample_data["name"] == "Bob"
    assert sample_data["age"] == 25







