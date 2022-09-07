package stepdefinitions;

import data.User;
import data.UserBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.DashboardPage;
import pages.OrgangeHRMLoginPage;

import static data.UserBuilder.adminUser;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginStepDefinitions extends StepDefinitions {
    private OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage(driver);
    private DashboardPage dashboardPage = new DashboardPage(driver);

    @Given("^the HHRR manager wants to login on OrangeHRM$")
    public void theHhrrManagerWantsToLoginOnOrangeHrm() {
        // nothing to do
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
        User adminUser = adminUser();
        loginPage.loginWithCredentials(adminUser);
    }

    @Then("^he/she should see the main dashboard$")
    public void heSheShouldSeeTheMainDashboard() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        assertThat(dashboardPage.profilePictureIsShown()).isEqualTo(true);
        assertThat(dashboardPage.profileFullNameIsShown()).isEqualTo(true);
    }

    @Given("^I am a logged in user$")
    public void iAmALoggedInUser() {
        loginPage.loginWithCredentials(adminUser());
    }

    @When("^I log out$")
    public void iLogOut() {
        dashboardPage.clickOnProfileFullName().then().clickOnLogout();
    }

    @Then("^I should see the login form$")
    public void iShouldSeeTheLoginForm() {
        assertThat(loginPage.loginButtonIsVisible()).isEqualTo(true);
    }
}