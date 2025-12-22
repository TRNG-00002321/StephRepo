from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager


def test_open_page():
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    # Set up driver automatic Chrome -- you can use other browesers as well
    driver.get("https://www.python.org")
    title = driver.title
    assert "Python" in title

def test_open_login_page():
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    # Set up driver automatic Chrome -- you can use other browesers as well
    driver.get("https://the-internet.herokuapp.com/login")
    title = driver.title
    assert "Internet" in title

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.service import Service
import time

def test_login_valid_credentials():
    # 1. Create Service object (update path if needed)
    service = Service(ChromeDriverManager().install())

    # 2. Start WebDriver using Service
    driver = webdriver.Chrome(service=service)

    try:
        # 3. Open the login page
        driver.get("https://the-internet.herokuapp.com/login")

        # 4. Enter username
        driver.find_element(By.ID, "username").send_keys("tomsmith")

        # 5. Enter password -- using literals
        driver.find_element("id", "password").send_keys("SuperSecretPassword!")

        # 6. Click Login button
        driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

        # 7. Small wait for page load
        time.sleep(2)

        # 8. Verify successful login
        assert "/secure" in driver.current_url

    finally:
        # 9. Close browser
        driver.quit()
