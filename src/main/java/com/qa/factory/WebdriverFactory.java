package com.qa.factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.utils.Constants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverFactory {
	private static final WebdriverFactory instance = new WebdriverFactory();
	private WebDriver driver;
	private WebdriverFactory() {
	}
	public static WebdriverFactory getInstance() {
		return instance;
	}
	private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
	public void setDriver(String browser) {
		/*String os_name = Constants.OS_NAME.substring(0,3).toLowerCase();
		String driverPath = "";
		String driverValue = "";
		String driverKey = "";*/
		browser = browser.toLowerCase();
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
			driver = new ChromeDriver();
			threadedDriver.set(driver);
		}
		else if(browser.equals("firefox")) {
			driver = WebDriverManager.firefoxdriver().create();
			threadedDriver.set(driver);
		}
		else if(browser.equals("edge")) {
			driver = WebDriverManager.edgedriver().create();
			threadedDriver.set(driver);
		}
		else {
			System.out.println("Browser not supported or not valid");
		}
		/*driverPath = Constants.PROJECT_PATH+"//Drivers//"+driverValue+((os_name.equals("win"))?".exe":"");
		System.setProperty(driverKey, driverPath);*/
		getDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		getDriver().manage().window().maximize();
	}
	public WebDriver getDriver() {
		/*WebDriver driver;
		browser = browser.toLowerCase();*/
		/*if(threadedDriver.get() == null) {
			if(browser.equals("chrome")) {
				driver = new ChromeDriver();
				threadedDriver.set(driver);
			}
			else if(browser.equals("firefox")) {
				driver = new FirefoxDriver();
				threadedDriver.set(driver);
			}
			else {
				System.out.println("Browser not valid or not supported");
			}
		}*/
		return threadedDriver.get();
	}
	public void quitDriver() {
		threadedDriver.get().quit();
		threadedDriver.set(null);
	}
}
