Feature: User Authentication
  As a registered user
  I want to log into my account
  so that I can access my personalized dashboard

  #background comes before every scenario in the feature
  #useful in common pre-conditions
Background:
  Given the application is running
  And the test database is already seeded with users

  #Scenario is like a test case with given/when/then
Scenario: Successful login with valid credentials
  #Given specifies pre-conditions
  Given the user is on the login page

  #When describes the action(s) being tested
  When the user enters username "tomsmith"
  And the user enters password "SuperSecretPassword!"
  And the user clicks the login button

  #Then describes the expected behavior/outcome
  Then the user should be redirected to the secure area
  And the the page should display a message containing "You logged in"