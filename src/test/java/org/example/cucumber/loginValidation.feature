@tag
Feature: Login error validation

  @Negative
  Scenario Outline: Positive Test of submitting the order
    Given I landed on Ecommerce page
    When Login with username <username> and password <passwd>
    Then <expectedErrorMessage> is displayed

    Examples:
      | username                       | passwd          | expectedErrorMessage         |
      | cristian.dolinta@eexample.com  | TTestPasswd1234 | Incorrect email or password. |