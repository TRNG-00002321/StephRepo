import pytest

@pytest.fixture(scope="module")
def sample_data():
    data = {"name": "Bob", "age": 25}
    yield data
    #do something