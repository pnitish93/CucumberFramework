package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utils.SeleniumUtilities;

public class BasePage {

	private WebDriver driver;
	private SeleniumUtilities utils;

	@FindBy(id = "twotabsearchtextbox")
	protected WebElement searchBox;

	@FindBy(id = "nav-search-submit-button")
	protected WebElement searchButton;
	
	@FindBy(xpath = "//select[@title='Search in']")
	protected WebElement searchCategory;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
		utils = new SeleniumUtilities(driver);
	}
	
	public boolean areSearchElementsDisplayed() {
		return utils.checkIfElementsDisplayed(searchBox, searchButton);
	}
	
	public ResultPage searchFor(String searchItem) {
		utils.typeInItems(searchBox, searchItem);
		utils.clickOnWebElement(searchButton);
		return new ResultPage(driver);
	}
	
	public HomePage clearAndClickOnSearch() {
		utils.clearField(searchBox);
		utils.clickOnWebElement(searchButton);
		return new HomePage(driver);
	}
	
	public void clickOnSearchButton() {
		utils.clickOnWebElement(searchButton);
	}
	
	public void selectCategoryForSearch() {
		
	}

}
