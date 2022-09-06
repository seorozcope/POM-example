package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
        features = "src/test/resources/features/EmployeesManagement.feature",
        glue = "stepdefinitions",
        tags = "@test",
        snippets = CAMELCASE
)
public class EmployeesManagementRunner extends AbstractTestNGCucumberTests {
}
