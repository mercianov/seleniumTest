@SmokeFeature1
Feature: Checkout
]
    
  @test5
  Scenario: User successfully checkout chosen item
    Given user has logged in
    When user add to cart 3 fix item
    And user click cart icon
    And user click checkout button
    And user fill in customer data
    And user click continue
    And user verify the checkout page
    And user click finish
    Then user has success checkout
    
   @test6
  Scenario: User successfully checkout random item
    Given user has logged in
    When user add to cart random item
    And user click cart icon
    And user click checkout button
    And user fill in customer data
    And user click continue
    And user verify the checkout page random order
    And user click finish
    Then user has success checkout
    