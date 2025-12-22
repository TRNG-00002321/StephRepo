import time

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from webdriver_manager.chrome import ChromeDriverManager


def demo_simple_approach():
   #Selenium 3 approach
    #driver = webdriver.Chrome()

    #Selenium 4+ approach
    service=Service(ChromeDriverManager().install())
    driver=webdriver.Chrome(service=service)
    #Set up driver automatic Chrome -- you can use other browesers as well
    driver.get("https://www.python.org")
    print(f"Page Title: {driver.title}")
    print(f"Current URL: {driver.current_url}")

    # Find an element and interact
    # Java: driver.findElement(By.id("id-search-field")).sendKeys("selenium");
    search_box = driver.find_element(By.ID, "id-search-field")
    search_box.send_keys("selenium")
    time.sleep(5)
    search_box.submit()


from contextlib import contextmanager


@contextmanager
def create_chrome_driver():
    """
    Context manager ensures browser closes even if errors occur.
    This is a Pythonic pattern not available in Java.

    Usage:
        with create_chrome_driver() as driver:
            driver.get("https://example.com")
            # Browser automatically closes after this block
    """
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service)
    try:
        yield driver
    finally:
        driver.quit()


def demo_context_manager_approach():
    """
    Using context manager for safe browser handling.
    Browser is guaranteed to close even if exceptions occur.
    """
    with create_chrome_driver() as driver:
        driver.get("https://www.selenium.dev/")
        print(f"Page Title: {driver.title}")

        # Find all navigation links using list comprehension
        # Java would need a loop: for (WebElement link : links) { ... }
        nav_links = driver.find_elements(By.CSS_SELECTOR, "nav a")
        link_texts = [link.text for link in nav_links if link.text.strip()]

        print(f"Navigation links found: {len(link_texts)}")
        for text in link_texts[:5]:  # Show first 5
            print(f"  - {text}")

        print("✓ Context manager demo completed successfully!")

    # Browser is automatically closed here - no explicit quit() needed!

# Class Based Approach
class SeleniumDemo:
    """
    Class-based approach for more structured test automation.
    Similar to Java class structure but more flexible.
    """

    def __init__(self):
        """Initialize the WebDriver - like Java constructor."""
        self.service = Service(ChromeDriverManager().install())
        self.driver = webdriver.Chrome(service=self.service)
        # Set implicit wait (10 seconds) - Java: driver.manage().timeouts().implicitlyWait()
        self.driver.implicitly_wait(10)

    def navigate_to(self, url: str) -> None:
        """Navigate to a URL."""
        self.driver.get(url)

    def get_title(self) -> str:
        """Get page title."""
        return self.driver.title

    def find_element_by_id(self, element_id: str):
        """Find element by ID."""
        return self.driver.find_element(By.ID, element_id)

    def close(self) -> None:
        """Close the browser."""
        self.driver.quit()


def demo_class_based_approach():
    """Demonstrate class-based Selenium usage."""
    demo = SeleniumDemo()
    try:
        demo.navigate_to("https://the-internet.herokuapp.com/")
        print(f"Page Title: {demo.get_title()}")

        # Find all example links
        links = demo.driver.find_elements(By.TAG_NAME, "a")
        print(f"Found {len(links)} links on the page")

        print("✓ Class-based demo completed successfully!")

    finally:
        demo.close()

#demo_simple_approach()
#demo_context_manager_approach()
demo_class_based_approach()
