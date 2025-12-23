"""
TODO: Implement a driver factory that:
1. Uses webdriver-manager for automatic driver management
2. Provides a context manager for safe browser cleanup
3. Supports headless mode via parameter
"""

from contextlib import contextmanager
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from webdriver_manager.chrome import ChromeDriverManager

@contextmanager
def create_chrome_driver(headless: bool = False):
    options = Options()
    if headless:
        options.add_argument("--headless")

    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service, options=options)
    
    try:
        yield driver
    finally:
        driver.quit()