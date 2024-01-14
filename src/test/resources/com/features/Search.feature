Feature: Search functionality 

Background: Common step of launching Amazon in the preferred browser
	Given I launch "https://www.amazon.in" in the preferred browser 

#Refer the 'Background' section for a common step
@SearchAndScroll
Scenario Outline: Searching products through top bar in the home page and through search bar in the search results page
	When I can see the search bar 
	And Type "<searchItems>" in the search bar and click on the search icon in the search bar 
	Then I should see a set of results according to my search 
	And I should be able to scroll down the results and navigate to next pages 
	When Type "<searchItems2>" in the search bar and click on the search icon in the search bar 
	Then I should see a set of results according to my search 
	And I should be able to scroll down the results and navigate to next pages
	And I can quit the browser
	
	Examples: 
	| searchItems | searchItems2 |
	| Jams  |  Books  |

#Refer the 'Background' section for a common step
@HomeFromSearchPage
Scenario Outline: Navigating to the home page using the search bar in the results page
   And Type "<searchItems>" in the search bar and click on the search icon in the search bar
   When I should see a set of results according to my search 
   And The user goes to the search bar, clears it and clicks on the search icon
   Then the user should be redirected to the home page
   And I can quit the browser

 #Replace the search items as per your choice
   Examples:
   |searchItems|
   |Laptop Bags|
   
#Refer the 'Background' section for a common step
@CategorySpecificSearch
Scenario Outline: User sees the result according to the category selected from the dropdown
	#And The category dropdown should be clickable
	When The user selects dropdown for "<dropdownValue>" from the category
	And clicks on search button
	Then The user should see results specific to the "<dropdownValue>" category
	And I can quit the browser

	Examples:
	|dropdownValue|
	|Books        |
	|Amazon Fresh |
	|Garden & Outdoors|
	|Amazon Fashion   |