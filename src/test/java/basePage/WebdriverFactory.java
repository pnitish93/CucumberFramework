package basePage;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Constants.Constants;

public class WebdriverFactory {
	private static final WebdriverFactory instance = new WebdriverFactory();
	private WebdriverFactory() {
	}
	public static WebdriverFactory getInstance() {
		return instance;
	}
	private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
	private void setDriver(String browser) {
		String os_name = Constants.OS_NAME.substring(0,3).toLowerCase();
		String driverPath = "";
		String driverValue = "";
		String driverKey = "";
		browser = browser.toLowerCase();
		if(browser.equals("chrome")) {
			driverValue = "chromedriver";
			driverKey = "webdriver.chrome.driver";
		}
		if(browser.equals("firefox")) {
			driverValue = "geckodriver";
			driverKey = "webdriver.gecko.driver";
		}
		driverPath = Constants.PROJECT_PATH+"//Drivers//"+driverValue+((os_name.equals("win"))?".exe":"");
		System.setProperty(driverKey, driverPath);
	}
	public WebDriver getDriver(String browser) {
		setDriver(browser);
		WebDriver driver;
		browser = browser.toLowerCase();
		if(threadedDriver.get() == null) {
			if(browser.equals("chrome")) {
				driver = new ChromeDriver();
				threadedDriver.set(driver);
			}
			else if(browser.equals("firefox")) {
				driver = new ChromeDriver();
				threadedDriver.set(driver);
			}
			else {
				System.out.println("Browser not valid or not supported");
			}
		}
		threadedDriver.get().manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		threadedDriver.get().manage().window().maximize();
		return threadedDriver.get();
	}
	public void quitDriver() {
		threadedDriver.get().quit();
		threadedDriver.set(null);
	}
}
