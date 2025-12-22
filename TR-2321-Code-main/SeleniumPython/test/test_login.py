from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC

LOGIN_URL = "https://the-internet.herokuapp.com/login"


def test_open_login_page(driver, open_url):
    open_url(LOGIN_URL)
    assert "Login Page" in driver.title


def test_login_valid_credentials(driver, wait, open_url, login):
    open_url(LOGIN_URL)
    login("tomsmith", "SuperSecretPassword!")

    wait.until(EC.url_contains("/secure"))

    success_message = wait.until(
        EC.visibility_of_element_located((By.CSS_SELECTOR, ".flash.success"))
    )

    assert "You logged into a secure area!" in success_message.text


def test_login_invalid_password(driver, wait, open_url, login):
    open_url(LOGIN_URL)
    login("tomsmith", "WrongPassword")

    error_message = wait.until(
        EC.visibility_of_element_located((By.CSS_SELECTOR, ".flash.error"))
    )

    assert "Your password is invalid!" in error_message.text


def test_login_invalid_username(driver, wait, open_url, login):
    open_url(LOGIN_URL)
    login("wronguser", "SuperSecretPassword!")

    error_message = wait.until(
        EC.visibility_of_element_located((By.CSS_SELECTOR, ".flash.error"))
    )

    assert "Your username is invalid!" in error_message.text


def test_login_blank_fields(driver, wait, open_url):
    open_url(LOGIN_URL)

    driver.find_element(By.CSS_SELECTOR, "button[type='submit']").click()

    error_message = wait.until(
        EC.visibility_of_element_located((By.CSS_SELECTOR, ".flash.error"))
    )

    assert "Your username is invalid!" in error_message.text
