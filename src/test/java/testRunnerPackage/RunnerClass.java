package testRunnerPackage;


import org.junit.runner.RunWith;
/*import cucumber.api.*;
import cucumber.api.junit.Cucumber;*/
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/com/features/"},
		glue = {"stepdefinitions", "appHooks"},
		//tags = "@CategorySpecificSearch",
		monochrome = true,
		dryRun = false,
		plugin = {"pretty", "html:test-output/report.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class RunnerClass {
	private static void main(String[] args) {
		System.out.println("Runner class is being executed");
	}
}