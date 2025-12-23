"""
Browser capability reporter.

Displays information about installed browsers and their capabilities.
"""

from utils.multi_browser_factory import create_driver


def generate_browser_report():
    """Generate a report of browser capabilities."""
    browsers = ["chrome", "firefox"]

    print("=" * 60)
    print("BROWSER CAPABILITY REPORT")
    print("=" * 60)

    for browser in browsers:
        print(f"\n{browser.upper()}")
        print("-" * 40)

        try:
            with create_driver(browser, headless=True) as driver:
                    caps = driver.capabilities
                    print(f"  Name:    {caps.get('browserName')}")
                    print(f"  Version: {caps.get('browserVersion') or caps.get('version')}")
                    print(f"  Platform: {caps.get('platformName') or caps.get('platform')}")
        except Exception as e:
            print(f"Not available: {e}")


if __name__ == "__main__":
    generate_browser_report()