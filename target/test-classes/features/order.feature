Feature: Product Order Feature
  As a User I want to checkout the products
  I've added to the cart and place the order, and should
  be able to cancel the order after placing it successfully


  @OrderTest
  Scenario: Successfully place and cancel the order
    Given I am on the Amazon home page with an item in the cart
    When I navigate to the order checkout page
    When I select the delivery date and time
    And I click on place the order option
    Then my order should be successfully placed

    Given I have placed an order successfully and on the order history page
    When I click on Cancel Placed Items
    Then my order items should successfully get cancelled