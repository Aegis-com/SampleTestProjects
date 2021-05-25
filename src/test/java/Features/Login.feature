Feature: Login page feature

  Background: 
  Given user is in login page
   
   Scenario: Login with Valid credentials
   	When user gets the title of the page
    Then page title should be "FTE Nominations"
    When User clicks profile button to login
    And User enters the username and clicks the Next button
    And User enters the password and clicks the Sign In button
    And User clicks Sign in with your phone or token device
    And user gets the text from the Homepage
    Then page text should be titled as "Microsoft Cloud Accelerator Program"

    

