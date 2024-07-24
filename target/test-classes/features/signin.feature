Feature: Sign-in Functionality for Amazon E-Commerce Website

  As a user of the Amazon E-Commerce Website
  I want to login with my account credentials
  So that I can access the account features and orders

  Background:
    Given I am on the Amazon login page

  @First @Smoke
  Scenario: Successful login with valid credentials
    Given I have entered a valid email
    |dhaksiny@buffalo.edu|

    And clicked on continue
    And I have entered a valid password
    |Dhaksu@555|

    When I click on the sign-in button
    Then I should be signed in to my account successfully

  @Second @Sanity
  Scenario Outline: Unsuccessful login with invalid or empty email address
    Given I have entered invalid "<email>"
    And clicked on continue
    Then I should see an error message indicating "<message>"

    Examples:
    |email                   |message                        |
    |afdfwevevwvv            |Wrong or Invalid email address or mobile phone number. Please correct and try again.|
    |                        |Enter your email or mobile phone number|

  @Third @Regression
  Scenario: Navigating to the forgotten password page
    Given I have entered a valid email
    |dhaksiny@buffalo.edu|
    And clicked on continue
    And I click on the "Forgot your password?" link
    Then I should be redirected to the password reset page


