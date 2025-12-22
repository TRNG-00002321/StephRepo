import os

from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chromium.options import ChromiumOptions
from webdriver_manager.chrome import ChromeDriverManager


def demo_browser_options():
    options = ChromiumOptions()

    # ---- Performance & Stability ----
    options.add_argument("--disable-gpu")
    options.add_argument("--no-sandbox")
    options.add_argument("--disable-dev-shm-usage")
    options.add_argument("--disable-extensions")

    # ---- Window Configuration ----
    options.add_argument("--start-maximized")
    # Or for headless: options.add_argument("--headless=new")

    # ---- Security & Privacy ----
    options.add_argument("--incognito")
    options.add_argument("--disable-notifications")
    options.add_argument("--disable-popup-blocking")

    # ---- Hide Automation ----
    options.add_experimental_option("excludeSwitches", ["enable-automation"])
    options.add_argument("--disable-blink-features=AutomationControlled")

    # ---- Preferences ----
    download_dir = os.path.join(os.getcwd(), "downloads")
    os.makedirs(download_dir, exist_ok=True)

    prefs = {
        "download.default_directory": download_dir,
        "download.prompt_for_download": False,
        "credentials_enable_service": False,
        "profile.password_manager_enabled": False,
    }
    options.add_experimental_option("prefs", prefs)

    # Create driver
    service = Service(ChromeDriverManager().install())
    driver = webdriver.Chrome(service=service, options=options)

    try:
        driver.get("https://www.selenium.dev/")

        print(f"✓ Production configuration applied:")
        print(f"  - Performance optimizations: ON")
        print(f"  - Incognito mode: ON")
        print(f"  - Automation hidden: ON")
        print(f"  - Custom download directory: {download_dir}")
        print(f"  - Page loaded: {driver.title}")

    finally:
        driver.quit()

    print("Production configuration demo completed\n")

demo_browser_options()