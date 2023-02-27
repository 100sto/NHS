@smokeTest   @regression
Feature: Login functionality for NHS Patient portal

  @NHS_1
  Scenario: Verify the the user is logged in successfully
    Given The user lands on NHS portal login page
    When The user enters correct credentials
    Then The user is on the NHS home page

  @NHS_1
  Scenario Outline: Verify the user cannot login with wrong credentials
    Given The user lands on NHS portal login page
    When The user enters wrong "<userName>" and "<password>"
    Then The user is still on the login page
    Examples:
      | userName | password |
      |          | wrong    |
      | wrong    |          |
      |          |          |