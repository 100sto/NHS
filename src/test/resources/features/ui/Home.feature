@smokeTest   @regression
Feature: Home page testing for NHS Patient portal

  @NHS-11
  Scenario: Verify the 3 tabs are displayed on the home page
    Given The user lands on NHS portal login page
    When The user enters correct credentials
    Then The user is on the NHS home page
    And The user can see the 3 tabs displayed on the home page