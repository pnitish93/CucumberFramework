package testRunnerPackage;

import org.junit.runner.RunWith;
import cucumber.api.*;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"./Features"},
		glue = {"stepdefinitions"},
		monochrome = true,
		dryRun = false,
		plugin = {"pretty", "html:test-output"},
		tags = {})
public class RunnerClass {
	private static void main(String[] args) {
		System.out.println("Runner class is being executed");
	}
}
