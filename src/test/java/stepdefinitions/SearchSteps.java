package stepdefinitions;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;

import com.pages.HeaderSection;
import com.pages.HomePage;
import com.pages.ResultPage;
import com.qa.factory.WebdriverFactory;
import com.qa.utils.SeleniumUtilities;

import org.junit.*;

import io.cucumber.java.en.*;

public class SearchSteps{
	String searchString;
	WebDriver driver = WebdriverFactory.getInstance().getDriver();
	HeaderSection header = new HeaderSection(driver);
	ResultPage results = new ResultPage(driver);
	SeleniumUtilities utils = new SeleniumUtilities(driver);
	ExtentReports searchReports = new ExtentReports();
	ExtentSparkReporter sparkSearch = new ExtentSparkReporter("../../../../test-output/SparkReport/SearchRelatedReport");
	
	@Given("I launch {string} in the preferred browser")
	public void i_launch_in_the_preferred_browser(String url) {
	    // Write code here that turns the phrase above into concrete actions
	    driver.get(url);
	}

	@When("I can see the search bar")
	public void i_can_see_the_search_bar() {
	    // Write code here that turns the phrase above into concrete actions
	    if(header.areSearchElementsDisplayed()) {
	    	System.out.println("Search Element is displayed");
	    }
	    else {
	    	System.out.println("Search Element is not displayed");
	    }
	}

	@When("Type {string} in the search bar and click on the search icon in the search bar")
	public void type_in_the_search_bar_and_click_on_the_search_icon_in_the_search_bar(String searchItem) {
	    // Write code here that turns the phrase above into concrete actions
		searchString = searchItem;
		results = header.enterTextAndSearchFor(searchItem);
	}

	@Then("I should see a set of results according to my search")
	public void i_should_see_a_set_of_results_according_to_my_search() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertTrue(results.areResultsAccordingToSearch(searchString));
	}

	@Then("I should be able to scroll down the results and navigate to next pages")
	public void i_should_be_able_to_scroll_down_the_results_and_navigate_to_next_pages() {
	    // Write code here that turns the phrase above into concrete actions
		results.scrollDownTheResultsNavigateNext();
	}

	@When("The user is on the search results page")
	public void the_user_is_on_the_search_results_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("The user goes to the search bar, clears it and clicks on the search icon")
	public void the_user_goes_to_the_search_bar_clears_it_and_clicks_on_the_search_icon() {
	    // Write code here that turns the phrase above into concrete actions
	    HomePage home = results.clearAndClickOnSearch();
	}

	@Then("the user should be redirected to the home page")
	public void the_user_should_be_redirected_to_the_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals("https://www.amazon.in/", utils.returnTheCurrentUrl());
	}

	@And("I can quit the browser")
	public void i_Can_Quit_The_Browser() {
		driver.quit();
	}

//	@And("The user sees the search bar and the dropdown for categories")
//	public void the_User_Sees_The_Search_Bar_And_The_Dropdown_For_Categories() {
//		Assert.assertTrue(header.checkSearchCategoryDisplayed());
//	}

	@When("The user selects dropdown for {string} from the category")
	public void the_User_Selects_Dropdown_For_From_The_Category(String categoryType) {
		header.selectValueFromSearchCategory(categoryType);
	}

	@And("clicks on search button")
	public void clicks_On_Search_Button() {
		header.clickOnSearchButton();
	}

	@Then("The user should see results specific to the {string} category")
	public void the_User_Should_See_Results_Specific_To_The_Category(String categoryType) {
		//--//span[contains(text(),'Alexa Skills') and contains(@class, 'bold')]
		//Exceptions - Amazon Fashion, Apps & Games, Audible Audiobooks, Garden & Outdoors (this element is present for garden - //span[contains(text(),'Garden') and contains(@class, 'bold')])
		//Exception - Garden & Outdoors (this element is present for garden - //span[contains(text(),'Garden') and contains(@class, 'bold')])
		//All the search dropdown options checked
		if (categoryType.equalsIgnoreCase("Garden & Outdoors")) {
			Assert.assertTrue(utils.checkIfAtLeastOneElementAvailable("xpath", "//span[contains(text(),'Garden') and contains(@class, 'bold')]"));
		}
		else if (categoryType.equalsIgnoreCase("Amazon Fashion")) {
			Assert.assertTrue(utils.checkIfAtLeastOneElementAvailable("xpath", "//img[@alt='Amazon Fashion']"));
		}
		else if (categoryType.equalsIgnoreCase("Apps & Games")) {
			Assert.assertTrue(utils.checkIfAtLeastOneElementAvailable("xpath", "//span[text()='Apps for Android']"));
		}
		else if (categoryType.equalsIgnoreCase("Audible Audiobooks")) {
			Assert.assertTrue(utils.checkIfAtLeastOneElementAvailable("xpath", "//span[contains(text(), 'Most Popular Audiobooks')]"));
		}
		else {
			Assert.assertTrue(utils.checkIfAtLeastOneElementAvailable("xpath", "//span[contains(text(), '"+categoryType+"')]"));
		}
	}

	@And("The category dropdown should be clickable")
	public void The_category_dropdown_should_be_clickable() {
		header.checkIfSearchCategoryIsClickable();
	}
}
