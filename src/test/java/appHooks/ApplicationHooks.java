package appHooks;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.WebdriverFactory;
import com.qa.utils.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {
	private WebDriver driver;
	private WebdriverFactory factory;
	private Properties prop;
	
	@Before(order = 0)
	public void getProperty() {
		ConfigReader config = new ConfigReader();
		prop = config.initProperties();
	}
	
	@Before(order = 1)
	public void getDriver() {
		factory = WebdriverFactory.getInstance();
		factory.setDriver(prop.getProperty("browser"));
		driver = factory.getDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@After(order = 1)
	public void tearDown(Scenario sc) {
		if(sc.isFailed()) {
			String screenshotName = sc.getName().replace(" ", "_");
			byte[] screenshotByteStream = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			sc.attach(screenshotByteStream, "image/png", screenshotName);
		}
	}
	
	@After(order = 0)
	public void quitDriver() {
		driver.quit();
	}

}
