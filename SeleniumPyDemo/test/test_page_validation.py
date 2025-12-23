"""
Test page content validation using Python Selenium.

Implement tests that:
1. Validate page title
2. Check for specific text content
3. Verify element presence
4. Check element attributes
"""

from selenium.webdriver.common.by import By

def test_page_title(home_page):
    """Verify the page title matches expected value."""
    assert home_page.title == "The Internet"

def test_heading_text(home_page):
    """Verify the main heading contains expected text."""
    heading = home_page.find_element(By.TAG_NAME, "h1").text
    assert heading == "Welcome to the-internet"

def test_links_present(home_page):
    """Verify that all example links are present on the page."""
    links = home_page.find_elements(By.CSS_SELECTOR, "li a")
    link_texts = [link.text for link in links]
    
    assert len(link_texts) > 0
    assert "A/B Testing" in link_texts
    assert "Checkboxes" in link_texts

def test_link_attributes(home_page):
    """Verify that links have correct href attributes."""
    ab_test_link = home_page.find_element(By.LINK_TEXT, "A/B Testing")
    href = ab_test_link.get_attribute("href")
    
    assert href is not None
    assert href.endswith("/abtest")