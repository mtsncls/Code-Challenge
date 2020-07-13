Feature: Customer Search

  As a Customer we want to see if the second ad from the second results page
  when searching for "Iphone" on www.aliexpress.com has at least 1 item to be bought.

  Scenario: Search Results
    Given I navigate to aliexpress page
    When I search for "Iphone" keyword
    And I navigate to the 2 results page
    And select the 2 ad
    Then I should see one item to bought

