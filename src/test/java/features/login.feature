Feature: Login into Appliication

  Scenario Outline: Positive test validating login
    Given Initialize the browser with chrome
    And Navigate to "http://qaclickacademy.com" site
    And Click on Login link in home page to land on Secure sing in Page
    When User enters <username> and <password> and logs in
    Then Verify that user is succesfully logged in
    And close browsers
    Examples:
    |username               |password     |
    |rodrigomtz85@gmail.com |Aguila00     |
    |rodrigomtz85@gmail.com |Aguila00     |