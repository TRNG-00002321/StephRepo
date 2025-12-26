@login @ui
Feature: User Login
  As a registered user
  I want to log in to the application
  So that I can access protected features

  Background:
    Given the user is on the login page

  @smoke @positive @critical
  Scenario: Successful login with valid credentials
    When the user logs in with username "tomsmith" and password "SuperSecretPassword!"
    Then the user should be redirected to the secure area
    And the user should see a success message containing "You logged into a secure area!"

  @regression @negative
  Scenario Outline: Failed login with invalid credentials
    When the user enters username "<username>"
    And the user enters password "<password>"
    And the user clicks the login button
    Then the user should remain on the login page
    And the user should see an error message containing "<error_message>"

    Examples: Invalid Credentials
      | username    | password             | error_message               |
      | tomsmith    | wrongpassword        | Your password is invalid!   |
      | invaliduser | SuperSecretPassword! | Your username is invalid!   |
      |             | SuperSecretPassword! | Your username is invalid!   |
      | tomsmith    |                      | Your password is invalid!   |