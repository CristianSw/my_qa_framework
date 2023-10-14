@tag
  Feature: Purchase the order from Ecommerce

    Background:
    Given I landed on Ecommerce page

    @Regression
    Scenario Outline: Positive Test of submitting the order
      Given Login with username <username> and password <passwd>
      When Add a product <productName> to cart
      And Checkout <productName>
      And Submit the order
      Then <expectedMessage> is displayed on confirmation page

      Examples:
      | username                     | passwd         | productName | expectedMessage         |
      | cristian.dolinta@example.com | TestPasswd1234 | ZARA COAT 3 | Thankyou for the order. |
