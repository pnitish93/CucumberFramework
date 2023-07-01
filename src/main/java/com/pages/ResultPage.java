package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utils.SeleniumUtilities;

public class ResultPage extends BasePage {
	private WebDriver driver;
	private SeleniumUtilities utils;

	@FindBy(xpath = "//span[contains(text(), 'results for')]")
	private WebElement resultsHeader;

	@FindBy(xpath = "//span[text()='Need help?']")
	private WebElement helpText;

	@FindBy(xpath = "//div[contains(@class, 's-result-item')]")
	private List<WebElement> elements;

	public ResultPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		utils = new SeleniumUtilities(driver);
	}

	public boolean areResultsAppearing() {
		return utils.checkIfElementsDisplayed(resultsHeader);
	}

	public void scrollDownTheResultsNavigateNext() {
		int pages = 0;
		int numElements;
		do {
			pages++;
			numElements = elements.size();
			for (int i = 0; i < numElements; i++) {
				utils.scrollToElement(elements.get(i));
				// It is the page navigator at the bottom
				if (driver.findElement(By.xpath("//div[contains(@cel_widget_id, 'MAIN-PAGINATION')]/parent::div"))
						.equals(elements.get(i))) {
					break;
				}
			}
			utils.ifElementEnabledThenClick("xpath",
					"//*[contains(text(), 'Next') and contains(@class, 'pagination-item') and not(contains(@class, 'disabled'))]");
		} while (utils.findElements("xpath",
				"//*[contains(text(), 'Next') and contains(@class, 'pagination-item') and not(contains(@class, 'disabled'))]")
				.size() != 0 && pages < 3);
		/*if (utils.findElements("xpath", "//li[contains(@class, 'a-last') and not(contains(@class, 'a-disabled'))]")
				.size() == 0) {
			numElements = elements.size();
			for (int i = 0; i < numElements; i++) {
				System.out.println(i);
				utils.scrollToElement(elements.get(i));
			}
		}*/
	}

	public boolean areResultsAccordingToSearch(String searchString) {
		String currentUrl = utils.returnTheCurrentUrl();
		WebElement searchItemHeading = utils.findElement("xpath",
				"//span[@class='a-color-state a-text-bold' and contains(text(), \"" + searchString + "\")]");
		WebElement resultText = utils.findElement("xpath", "//span[contains(text(), 'results for')]");
		return utils.checkIfElementsDisplayed(searchItemHeading, resultText);
	}
}
