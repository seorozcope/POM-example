package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.OrgangeHRMLoginPage;

import static stepdefinitions.Hooks.getDriver;

public class LoginStepDefinitions {
    OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage();

    @Given("^the HHRR manager wants to login on OrangeHRM$")
    public void theHhrrManagerWantsToLoginOnOrangeHrm() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }
    @When("^he\\/she provides wrong credentials$")
    public void heSheProvidesWrongCredentials() {

    }
    @Then("^he\\/she should see an (.*) message$")
    public void heSheShouldSeeAnInvalidCredentialsMessage(String expectedErrorMessage) {

    }
}