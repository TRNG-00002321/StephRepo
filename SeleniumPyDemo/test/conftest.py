import pytest
import os
import sys
from pathlib import Path

# Add project root to sys.path to allow importing from utils
sys.path.insert(0, str(Path(__file__).parent.parent))

from utils.driver_factory import create_chrome_driver

@pytest.fixture
def driver():
    """Fixture to provide a WebDriver instance."""
    with create_chrome_driver() as driver:
        yield driver

@pytest.fixture
def home_page(driver):
    """Fixture to navigate to the base URL and return the driver."""
    driver.get("https://the-internet.herokuapp.com/")
    return driver

@pytest.fixture(scope="session", autouse=True)
def create_screenshot_dir():
    """Ensure screenshots directory exists."""
    if not os.path.exists("screenshots"):
        os.makedirs("screenshots")