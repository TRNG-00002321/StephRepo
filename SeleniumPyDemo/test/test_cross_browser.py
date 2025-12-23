"""
Cross-browser compatibility tests.

Run the same tests across Chrome, Firefox, and Edge.
"""

import pytest
from selenium.webdriver.common.by import By
import sys

sys.path.insert(0, '..')
from utils.multi_browser_factory import create_driver

# Parameterize tests to run on multiple browsers
BROWSERS = ["chrome", "firefox"]  # Add "edge" if installed


@pytest.mark.parametrize("browser", BROWSERS)
def test_page_loads_correctly(browser):
    """
    Verify the page loads correctly in each browser.
    """
    try:
        with create_driver(browser, headless=True) as driver:
            driver.get("https://the-internet.herokuapp.com/")
            assert "The Internet" in driver.title
    except Exception as e:
        pytest.skip(f"Browser {browser} might not be installed: {e}")


@pytest.mark.parametrize("browser", BROWSERS)
def test_form_interaction(browser):
    """
    Verify form interaction works in each browser.

    Steps:
    1. Navigate to login page
    2. Enter credentials
    3. Submit form
    4. Verify result
    """
    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/login")

        driver.find_element(By.ID, "username").send_keys("tomsmith")
        driver.find_element(By.ID, "password").send_keys("SuperSecretPassword!")
        driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

        flash_message = driver.find_element(By.ID, "flash").text
        assert "You logged into a secure area!" in flash_message


@pytest.mark.parametrize("browser", BROWSERS)
def test_screenshot_capture(browser):
    """
    Verify screenshot capture works in each browser.

    Steps:
    1. Navigate to a page
    2. Take screenshot
    3. Verify file was created
    """
    import os
    if not os.path.exists("screenshots"):
        os.makedirs("screenshots")

    filename = f"screenshots/{browser}_screenshot.png"

    with create_driver(browser, headless=True) as driver:
        driver.get("https://the-internet.herokuapp.com/")
        driver.save_screenshot(filename)

    assert os.path.exists(filename)