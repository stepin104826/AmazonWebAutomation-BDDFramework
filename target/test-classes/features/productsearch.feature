Feature: Amazon Product search functionality

  As an Amazon user I want to search
  my product of choice and be able to
  add it to the shopping cart


  Background:
    Given I am on the Amazon Homepage

  @ProductSearchTest
  Scenario Outline:  As a user I should be able to search products, add them to cart
    Given I have entered the product search phrase "<phrase>" and have clicked the search icon
    Then I should be shown relevant products as results
    When I have selected a product
    And "Add to cart" is clicked
    Then the correct product is added to the cart

    Examples:
    |phrase|
    |Shoes for Men|
    |Gym outfit for Men  |
    |Apple Watches       |



    




