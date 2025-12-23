"""
first_selenium_test.py
A simple script demonstrating Python Selenium basics
"""
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

# Setup Chrome driver with automatic driver management
service = Service(ChromeDriverManager().install())
driver = webdriver.Chrome(service=service)

try:
    # Navigate to a webpage
    driver.get("https://www.python.org")

    # Print the page title
    print(f"Page Title: {driver.title}")

    # Find an element and interact with it
    search_box = driver.find_element(By.ID, "id-search-field")
    search_box.send_keys("selenium")

    # Submit the search
    search_box.submit()

    # Print the new URL
    print(f"Current URL: {driver.current_url}")

finally:
    # Always close the browser
    driver.quit()