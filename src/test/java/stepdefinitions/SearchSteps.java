package stepdefinitions;


import org.openqa.selenium.WebDriver;

import com.pages.BasePage;
import com.pages.HomePage;
import com.pages.ResultPage;
import com.qa.factory.WebdriverFactory;
import com.qa.utils.Constants;
import com.qa.utils.SeleniumUtilities;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import io.cucumber.java.en.*;

public class SearchSteps{
	String searchString;
	WebDriver driver = WebdriverFactory.getInstance().getDriver();
	BasePage base = new BasePage(driver);
	ResultPage results = new ResultPage(driver);
	SeleniumUtilities utils = new SeleniumUtilities(driver);
	
	@Given("I launch {string} in the preferred browser")
	public void i_launch_in_the_preferred_browser(String url) {
	    // Write code here that turns the phrase above into concrete actions
	    driver.get(url);
	}

	@When("I can see the search bar")
	public void i_can_see_the_search_bar() {
	    // Write code here that turns the phrase above into concrete actions
	    if(base.areSearchElementsDisplayed()) {
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
		results = base.searchFor(searchItem);
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
}
