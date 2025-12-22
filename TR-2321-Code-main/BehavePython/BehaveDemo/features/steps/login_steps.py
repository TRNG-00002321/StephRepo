# from behave import given, when, then
# from selenium import webdriver
# from selenium.webdriver.chrome.options import Options
# from selenium.webdriver.common.by import By
# from selenium.webdriver.support.wait import WebDriverWait
# from selenium.webdriver.support import expected_conditions as EC
#
# BASE_URL = "https://the-internet.herokuapp.com"
#
#
# # ---------- GIVEN STEPS ----------
#
# @given("the application is running")
# def step_application_running(context):
#     """
#     Assert that the application is reachable and responding.
#     We do this by opening the home page and checking for a known element.
#     """
#     options = Options()
#     options.add_argument("--start-maximized")
#
#     context.driver = webdriver.Chrome(options=options)
#     context.wait = WebDriverWait(context.driver, 10)
#
#     context.driver.get(BASE_URL)
#
#     # Assert the application loaded by checking page heading
#     heading = context.wait.until(
#         EC.visibility_of_element_located((By.TAG_NAME, "h1"))
#     )
#     assert heading.text == "Welcome to the-internet"
#
#
# @given("the test database is seeded with users")
# def step_database_seeded(context):
#     # Placeholder for demo purposes
#     # In real systems this might call an API or run a script
#     assert True
#
#
# @given("the user is on the login page")
# def step_user_on_login_page(context):
#     context.driver.get(f"{BASE_URL}/login")
#
#     # Assert login page loaded
#     context.wait.until(
#         EC.visibility_of_element_located((By.ID, "login"))
#     )
#
#
# # ---------- WHEN STEPS ----------
#
# @when('the user enters username "tomsmith"')
# def step_enter_username(context):
#     username_input = context.wait.until(
#         EC.visibility_of_element_located((By.ID, "username"))
#     )
#     username_input.clear()
#     username_input.send_keys("tomsmith")
#
#
# @when('the user enters password "SuperSecretPassword!"')
# def step_enter_password(context):
#     password_input = context.wait.until(
#         EC.visibility_of_element_located((By.ID, "password"))
#     )
#     password_input.clear()
#     password_input.send_keys("SuperSecretPassword!")
#
#
# @when("the user clicks the login button")
# def step_click_login(context):
#     login_button = context.wait.until(
#         EC.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']"))
#     )
#     login_button.click()
#
#
# # ---------- THEN STEPS ----------
#
# @then("the user should be redirected to the secure area")
# def step_redirected_to_secure_area(context):
#     context.wait.until(
#         EC.url_contains("/secure")
#     )
#     assert "/secure" in context.driver.current_url
#
#
# @then('the page should display message containing "You logged into a secure area!"')
# def step_verify_success_message(context):
#     flash_message = context.wait.until(
#         EC.visibility_of_element_located((By.ID, "flash"))
#     )
#     assert "You logged into a secure area!" in flash_message.text
#
#     context.driver.quit()