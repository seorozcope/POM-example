package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
        glue = "stepdefinitions",
        features = "src/test/resources/features/Login.feature",
        snippets = CAMELCASE
)
public class LoginRunner extends AbstractTestNGCucumberTests {
}
