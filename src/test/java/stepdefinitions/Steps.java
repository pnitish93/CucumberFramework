package stepdefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import Constants.Constants;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.After;
import basePage.BasePage;
import basePage.WebdriverFactory;
import pageObjects.HomePage;
import pageObjects.ResultPage;

public class Steps {
	
	WebDriver driver;
	BasePage base;
	ResultPage results;
	HomePage home;
	WebdriverFactory driverFactory;
	
	@Before
	public void beforeConfigurations() {
		//System.setProperty("webdriver.chrome.driver", Constants.PROJECT_PATH+"/Drivers/chromedriver.exe");
		driverFactory = WebdriverFactory.getInstance();
		driver = driverFactory.getDriver("Chrome");
	}
	
	@Given("^I launch \"([^\"]*)\" in the preferred browser$")
	public void i_launch_in_the_preferred_browser(String url) {
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		base = new BasePage(driver);
		driver.manage().window().maximize();
	}

	@When("^I can see the search bar$")
	public void i_can_see_the_search_bar() {
	    Assert.assertTrue(base.areSearchBarAndButtonDisplayed());
	}

	@When("^Type \"([^\"]*)\" in the search bar and click on the search icon in the search bar$")
	public void type_in_the_search_bar_and_click_on_the_search_icon_in_the_search_bar(String searchItem) {
	    results = base.searchFor(searchItem);
	}

	@Then("^I should see a set of results according to my search$")
	public void i_should_see_a_set_of_results_according_to_my_search() {
	    Assert.assertTrue(results.areResultsAppearing());
	}

	@Then("^I should be able to scroll down the results and navigate to next pages$")
	public void i_should_be_able_to_scroll_down_the_results_and_navigate_to_next_pages() {
	    results.scrollDownTheResultsNavigateNext();
	}
	
	@Given("^The user is on the search results page$")
	public void the_user_is_on_the_search_results_page() {
	    Assert.assertTrue(results.areResultsAppearing());
	}

	@When("^The user goes to the search bar, clears it and clicks on the search icon$")
	public void the_user_goes_to_the_search_bar_and_clears_it_and_clicks_on_the_seach_icon() {
		home = base.clearAndClickOnSearch();
	}

	@Then("^the user should be redirected to the home page$")
	public void the_user_should_be_redirected_to_the_home_page() {
	    Assert.assertEquals("https://www.amazon.in/", home.returnCurrentUrl());
	}
	
	@After
	public void afterConfigurations() {
		driverFactory.quitDriver();
	}
}
