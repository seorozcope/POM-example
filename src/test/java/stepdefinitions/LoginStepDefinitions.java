package stepdefinitions;

import data.User;
import data.UserBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.OrgangeHRMLoginPage;

import static org.assertj.core.api.Assertions.assertThat;
import static stepdefinitions.Hooks.getDriver;

public class LoginStepDefinitions extends StepDefinitions {
    private OrgangeHRMLoginPage loginPage;

    @Given("^the HHRR manager wants to login on OrangeHRM$")
    public void theHhrrManagerWantsToLoginOnOrangeHrm() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new OrgangeHRMLoginPage(driver);
    }

    @When("^he/she provides wrong credentials$")
    public void heSheProvidesWrongCredentials() {
        User wrongUser = UserBuilder.wrongCredentials();
        loginPage.loginWithCredentials(wrongUser);
    }

    @Then("^he/she should see an (.*) message$")
    public void heSheShouldSeeAnInvalidCredentialsMessage(String expectedErrorMessage) {
        assertThat(loginPage.getShowedErrorMessage()).isEqualTo(expectedErrorMessage);
    }

    @When("^he/she provides valid credentials$")
    public void heSheProvidesValidCredentials() {
        User adminUser = UserBuilder.adminUser();
        loginPage.loginWithCredentials(adminUser);
    }

    @Then("^he/she should see the main dashboard$")
    public void heSheShouldSeeTheMainDashboard() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        assertThat(dashboardPage.profilePictureIsShown()).isEqualTo(true);
        assertThat(dashboardPage.profileFullNameIsShown()).isEqualTo(true);
    }
}