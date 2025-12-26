#@login @ui @authentication
#Feature: User Authentication
#  As a registered user of the application
#  I want to be able to log in to my account
#  So that I can access my personalized content
#
#  Background:
#    Given the user is on the login page
#
#  @smoke @positive @p1
#  Scenario: Successful login with valid credentials
#    When the user enters username "tomsmith"
#    And the user enters password "SuperSecretPassword!"
#    And the user clicks the login button
#    Then the user should be redirected to the secure area
#    And the user should see a success message containing "You logged into a secure area!"
#
#  @negative @headless @p2
#  Scenario: Failed login with invalid password
#    When the user enters username "tomsmith"
#    And the user enters password "wrongpassword"
#    And the user clicks the login button
#    Then the user should remain on the login page
#    And the user should see an error message containing "Your password is invalid!"
#
#  @negative @headless @p2
#  Scenario: Failed login with invalid username
#    # TODO: Implement this scenario
#    # Use username "invaliduser" and valid password format
#    When the user enters username "invaliduser"
#    And the user enters password "SuperSecretPassword!"
#    And the user clicks the login button
#    Then the user should remain on the login page
#    And the user should see an error message containing "Your username is invalid!"
#
#  @negative @headless @p2
#  Scenario: Failed login with empty credentials
#    # TODO: Implement this scenario
#    # Try to login with empty username and password
#    When the user enters username ""
#    And the user enters password ""
#    And the user clicks the login button
#    Then the user should remain on the login page
#    And the user should see an error message containing "Your username is invalid!"
#
#
#
#
##Feature: User Authentication
##  As a registered user
##  I want to log into my account
##  so that I can access my personalized dashboard
##
##  #background comes before every scenario in the feature
##  #useful in common pre-conditions
##Background:
##  Given the application is running
##  And the test database is already seeded with users
##
##  #Scenario is like a test case with given/when/then
##Scenario: Successful login with valid credentials
##  #Given specifies pre-conditions
##  Given the user is on the login page
##
##  #When describes the action(s) being tested
##  When the user enters username "tomsmith"
##  And the user enters password "SuperSecretPassword!"
##  And the user clicks the login button
##
##  #Then describes the expected behavior/outcome
##  Then the user should be redirected to the secure area
##  And the the page should display a message containing "You logged in"