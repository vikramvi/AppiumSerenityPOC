Feature: Wine App scenarios


Scenario: User is able to goto landing page from splash screens to do search with valid wine name
Given User completes splash screen action
When User enters valid wine name
And  User selects wine name from search result
Then User can do sort action
