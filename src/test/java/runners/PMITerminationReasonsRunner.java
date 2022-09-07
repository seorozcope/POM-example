package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import static io.cucumber.testng.CucumberOptions.SnippetType.CAMELCASE;

@CucumberOptions(
        features = "src/test/resources/features/PMITerminationReasons.feature",
        glue = "stepdefinitions",
        snippets = CAMELCASE
)
public class PMITerminationReasonsRunner extends AbstractTestNGCucumberTests {
}
