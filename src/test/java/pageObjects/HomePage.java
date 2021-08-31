package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import basePage.BasePage;

import utilities.SeleniumUtilities;

public class HomePage extends BasePage{
	WebDriver driver;
	SeleniumUtilities utils;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utils = new SeleniumUtilities(driver);
	}
	
	public String returnCurrentUrl() {
		return utils.returnTheCurrentUrl();
	}
}
