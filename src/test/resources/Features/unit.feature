@SmokeFeature
Feature: Login

  @test9
  Scenario: User successfully upload file
    Given user is on website to upload file
    When user choose file
    And user click upload button
    Then user get success upload message
