package com.qa.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtilities {
	WebDriver driver;
	JavascriptExecutor js;
	
	public SeleniumUtilities(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)driver;
	}
	
	public WebElement findElement(String locatorType, String locatorValue) {
		By by = null;
		WebElement ele = null;
		if(locatorType.equalsIgnoreCase("id")) {
			by = By.id(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("class")) {
			by = By.className(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("cssselector")) {
			by = By.cssSelector(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("name")) {
			by = By.name(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("tag")) {
			by = By.tagName(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("xpath")) {
			by = By.xpath(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("linktext")) {
			by = By.linkText(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("partiallinktext")) {
			by = By.partialLinkText(locatorValue);
		}
		else {
			System.out.println("Not a valid locator");
			return ele;
		}
		try {
			ele = driver.findElement(by);
		}
		catch(Exception e) {
			System.out.println("No such element exists. Please try again with valid locators.");
		}
		return ele;
	}
	
	public List<WebElement> findElements(String locatorType, String locatorValue){
		By by = null;
		List<WebElement> elements;
		if(locatorType.equalsIgnoreCase("id")) {
			by = By.id(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("class")) {
			by = By.className(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("cssselector")) {
			by = By.cssSelector(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("name")) {
			by = By.name(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("tag")) {
			by = By.tagName(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("xpath")) {
			by = By.xpath(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("linktext")) {
			by = By.linkText(locatorValue);
		}
		else if(locatorType.equalsIgnoreCase("partiallinktext")) {
			by = By.partialLinkText(locatorValue);
		}
		else {
			System.out.println("Not a valid locator");
		}
		elements = driver.findElements(by);
		return elements;
	}
	
	public boolean checkIfElementsDisplayed(WebElement ... ele) {
		boolean result = true;
		for(WebElement e:ele) {
			System.out.println(e.isDisplayed());
			result = result && e.isDisplayed();
		}
		return result;
	}
	
	public void typeInItems(WebElement typingField, String textToType) {
		typingField.clear();
		typingField.sendKeys(textToType);
	}
	
	public void clickOnWebElement(WebElement clickableElement) {
		clickableElement.click();
		try {
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void scrollDown(WebDriver driver1) {
		try{
			long height = (Long)js.executeScript("return window.innerHeight;");
			js.executeScript("window.scrollBy(0, arguments[0]);", height);
			Thread.sleep(2000);
		}
		catch(InterruptedException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void scrollToElement(WebElement element) {
		try {
			js.executeScript("arguments[0].scrollIntoView(true)", element);
			Thread.sleep(500);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ifElementEnabledThenClick(String locator, String locatorValue) {
		WebElement ele = findElement(locator, locatorValue);
		try {
			if(ele.isEnabled()) {
				ele.click();
			}
		}
		catch(Exception e) {
			System.out.println("Element does not exist.");
		}
		
	}	
	
	public void clearField(WebElement element) {
		element.clear();
		try {
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String returnTheCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public void selectRandomValueFromDropdown(WebElement dropdown, int indexToExclude) {
		Select selectElement = new Select(dropdown);
		List<WebElement> elements = selectElement.getOptions();
		int numberOfOptions = elements.size();
		if(indexToExclude >= 0) {
			if(indexToExclude > (numberOfOptions-1)) {
				System.out.println("Index chosen falls beyond the total number of options available.");
			}
			else {
			}
		}
	}
	
}
