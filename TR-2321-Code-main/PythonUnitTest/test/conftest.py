# content of conftest.py
import pytest

@pytest.fixture(scope="module")
def sample_data():
    """A fixture providing sample data for tests."""
    data = {"name": "Alice", "age": 30}
    print("\nSetting up sample_data fixture...")
    yield data  # The test runs here
    print("Tearing down sample_data fixture...")
