#Feature: User Authentication
#  As a registered user
#  I want to log into my account
#  So that I can access my personalized dashboard
#
#  # Background runs before EVERY scenario in this feature
#  # Use for common preconditions
#  Background:
#    Given the application is running
#    And the test database is seeded with users
#
#  # Basic scenario with Given/When/Then
#  Scenario: Successful login with valid credentials
#    # Given - Sets up the preconditions
#    Given the user is on the login page
#
#    # When - Describes the action being tested
#    When the user enters username "tomsmith"
#    And the user enters password "SuperSecretPassword!"
#    And the user clicks the login button
#
#    # Then - Describes the expected outcome
#    Then the user should be redirected to the secure area
#    And the page should display message containing "You logged into a secure area!"