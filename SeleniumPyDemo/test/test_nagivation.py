"""
Test navigation functionality using Python Selenium.

Implement tests that:
1. Navigate to https://the-internet.herokuapp.com/
2. Click on "Form Authentication" link
3. Verify URL changed to /login
4. Use back/forward navigation
5. Capture screenshots at key points
"""

from selenium.webdriver.common.by import By
import os

def test_navigate_to_login_page(home_page):
    """Test: Navigate from home to login page"""
    home_page.find_element(By.LINK_TEXT, "Form Authentication").click()
    
    assert "/login" in home_page.current_url
    heading = home_page.find_element(By.TAG_NAME, "h2").text
    assert heading == "Login Page"

def test_back_forward_navigation(home_page):
    """Test: Browser navigation (back/forward)"""
    home_url = home_page.current_url
    
    home_page.find_element(By.LINK_TEXT, "Checkboxes").click()
    checkbox_url = home_page.current_url
    
    home_page.back()
    assert home_page.current_url == home_url
    
    home_page.forward()
    assert home_page.current_url == checkbox_url

def test_capture_screenshot(home_page):
    """Test: Screenshot capture"""
    path = "screenshots/homepage.png"
    home_page.save_screenshot(path)
    assert os.path.exists(path)