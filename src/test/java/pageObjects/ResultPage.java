package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import basePage.BasePage;

import utilities.SeleniumUtilities;

public class ResultPage extends BasePage{
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
			for(int i = 0; i < numElements; i++) {
				utils.scrollToElement(elements.get(i));
				if(driver.findElement(By.xpath("//span[contains(@data-cel-widget, 'MAIN-PAGINATION')]//parent::div")).equals(elements.get(i))) {
					break;
				}
			}
			utils.ifElementEnabledThenClick("xpath", "//a[contains(text(), 'Next')]");
		} while(utils.findElements("xpath", "//li[contains(@class, 'a-last') and not(contains(@class, 'a-disabled'))]").size() != 0 && pages <= 3);
		if(utils.findElements("xpath", "//li[contains(@class, 'a-last') and not(contains(@class, 'a-disabled'))]").size() == 0) {
			numElements = elements.size();
			for(int i = 0; i < numElements; i++) {
				System.out.println(i);
				utils.scrollToElement(elements.get(i));
			}
		}
	}
}
