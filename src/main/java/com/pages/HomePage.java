package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.utils.SeleniumUtilities;

public class HomePage extends HeaderSection {
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
