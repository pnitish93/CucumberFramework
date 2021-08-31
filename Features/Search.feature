Feature: Search functionality 

@SearchAndScroll
Scenario Outline: Searching products through top bar in the home page and through search bar in the search results page
	Given I launch "https://www.amazon.in" in the preferred browser 
	When I can see the search bar 
	And Type "<searchItems>" in the search bar and click on the search icon in the search bar 
	Then I should see a set of results according to my search 
	And I should be able to scroll down the results and navigate to next pages 
	When Type "<searchItems2>" in the search bar and click on the search icon in the search bar 
	Then I should see a set of results according to my search 
	And I should be able to scroll down the results and navigate to next pages 
	
	Examples: 
	| searchItems | searchItems2 |
	| Jams  |  Books  |

@HomeFromSearchPage
Scenario Outline: Navigating to the home page using the search bar in the results page
   Given I launch "https://www.amazon.in" in the preferred browser 
   And Type "<searchItems>" in the search bar and click on the search icon in the search bar
   When The user is on the search results page
   And The user goes to the search bar, clears it and clicks on the search icon
   Then the user should be redirected to the home page
   
   Examples:
   |searchItems|
   |Men's shoes|