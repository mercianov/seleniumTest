@SmokeFeature
Feature: Login

  @test1
  Scenario: User login successfully with correct username and password
    Given user is on website login page
    And user enter correct username and correct password
    When click login button
    Then user successfully login
    And user is one website item product list

	@test2
  Scenario: User login successfully with wrong password
    Given user is on website login page
    When user enter correct username and wrong password
    And click login button
    Then user get error wrong password on login page
    
  @test3
	Scenario Outline: User login with blank password
    Given user is on website login page
    When user enter correct username and blank password
    And click login button
    Then user get error blank password on login page
    
  @test4
	Scenario Outline: User login with blank username and password
    Given user is on website login page
    When user enter blank username and blank password
    And click login button
    Then user get error blank username on login page

  