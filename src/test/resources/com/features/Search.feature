Feature: Search functionality 

Background: Common step
	Given I launch "https://www.amazon.in" in the preferred browser 

@SearchAndScroll
Scenario Outline: Searching products through top bar in the home page and through search bar in the search results page
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
   And Type "<searchItems>" in the search bar and click on the search icon in the search bar
   When I should see a set of results according to my search 
   And The user goes to the search bar, clears it and clicks on the search icon
   Then the user should be redirected to the home page
   
   Examples:
   |searchItems|
   |Men's shoes|
   
#@CategorySpecificSearch
#Scenario: User sees the result according to the category selected from the dropdown
#	Given I launch "https://www.amazon.in" in the preferred browser
#	And The user sees the search bar and the dropdown for categories
#	When The user selects any dropdown from the category
#	And clicks on search
#	Then The user should see results specific to the chosen category