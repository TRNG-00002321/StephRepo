"""
Multi-browser driver factory with webdriver-manager.

Supports:
- Chrome
- Firefox
- Edge
"""

from contextlib import contextmanager
from selenium import webdriver
from selenium.webdriver.chrome.options import Options as ChromeOptions
from selenium.webdriver.firefox.options import Options as FirefoxOptions
from selenium.webdriver.edge.options import Options as EdgeOptions
from selenium.webdriver.chrome.service import Service as ChromeService
from selenium.webdriver.firefox.service import Service as FirefoxService
from selenium.webdriver.edge.service import Service as EdgeService
from webdriver_manager.chrome import ChromeDriverManager
from webdriver_manager.firefox import GeckoDriverManager
from webdriver_manager.microsoft import EdgeChromiumDriverManager
import os

@contextmanager
def create_driver(browser: str = "chrome", headless: bool = False):
    """
    Create a WebDriver instance for the specified browser.

    Args:
        browser: Browser name ("chrome", "firefox", "edge")
        headless: Run in headless mode if True

    Yields:
        WebDriver instance

    Example:
        with create_driver("firefox", headless=True) as driver:
            driver.get("https://example.com")
    """
    driver = None

    try:
        if browser.lower() == "chrome":
            # TODO: Implement Chrome driver setup
            # 1. Create ChromeOptions
            # 2. Add headless argument if needed
            # 3. Use ChromeDriverManager for automatic driver download
            options = ChromeOptions()
            if headless:
                options.add_argument("--headless")
            
            service = ChromeService(ChromeDriverManager().install())
            driver = webdriver.Chrome(service=service, options=options)

        elif browser.lower() == "firefox":
            options = FirefoxOptions()
            if headless:
                options.add_argument("-headless")
            
            service = FirefoxService(GeckoDriverManager().install())
            driver = webdriver.Firefox(service=service, options=options)

        elif browser.lower() == "edge":
            options = EdgeOptions()
            if headless:
                options.add_argument("--headless")
            
            service = EdgeService(EdgeChromiumDriverManager().install())
            driver = webdriver.Edge(service=service, options=options)

        else:
            raise ValueError(f"Unsupported browser: {browser}")

        driver.implicitly_wait(10)
        yield driver

    finally:
        if driver:
            driver.quit()


def get_browser_version(browser: str) -> str:
    """
    Get the installed browser version.
    """
    with create_driver(browser, headless=True) as driver:
        return driver.capabilities['browserVersion']