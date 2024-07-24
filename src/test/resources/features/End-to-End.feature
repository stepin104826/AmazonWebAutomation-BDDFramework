Feature: Amazon Order Flow
  As a User, I want to search for a product, add it to the cart,
  checkout, place the order, and then cancel the order if needed.

  Background:
    Given I am on the Amazon Login Page

  @E2E
  Scenario Outline: End-to-end order and cancel flow
    When I provide a valid "<email>", click on continue
    And provide a valid "<password>", click on sign-in
    And I search for "<phrase>" in the product search
    And I select a product from the search results and add to the cart
    Then I should see the product added in the cart

    When I proceed to checkout
    And I select delivery date and time
    And I place the order
    Then my order shd be successfully placed

    When I navigate to the order history page
    And I cancel the placed order
    Then my order should be successfully cancelled

    Examples:
    |email               |password  |phrase       |
    |dhaksiny@buffalo.edu|Dhaksu@555|Sport Shoes under 15$|