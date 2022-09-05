package stepdefinitions;

import data.User;
import data.UserBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.OrgangeHRMLoginPage;

import static org.assertj.core.api.Assertions.assertThat;
import static stepdefinitions.Hooks.getDriver;

public class LoginStepDefinitions {
    private final WebDriver driver = getDriver();
    private OrgangeHRMLoginPage loginPage;

    @Given("^the HHRR manager wants to login on OrangeHRM$")
    public void theHhrrManagerWantsToLoginOnOrangeHrm() {
        getDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new OrgangeHRMLoginPage(driver);
    }
    @When("^he/she provides wrong credentials$")
    public void heSheProvidesWrongCredentials() {
        User wrongUser= UserBuilder.wrongCredentials();
        loginPage.enterTheUsername(wrongUser.getUsername())
                .then().enterThePassword(wrongUser.getPassword())
                .then().clickOnLogin();
    }
    @Then("^he/she should see an (.*) message$")
    public void heSheShouldSeeAnInvalidCredentialsMessage(String expectedErrorMessage) {
        assertThat(loginPage.getShowedErrorMessage()).isEqualTo(expectedErrorMessage);
    }

    @When("^he/she provides valid credentials$")
    public void heSheProvidesValidCredentials() {
        User wrongUser= UserBuilder.adminUser();
        loginPage.enterTheUsername(wrongUser.getUsername())
                .then().enterThePassword(wrongUser.getPassword())
                .then().clickOnLogin();
    }
    @Then("^he/she should see the main dashboard$")
    public void heSheShouldSeeTheMainDashboard() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        assertThat(dashboardPage.profilePictureIsShown()).isEqualTo(true);
        assertThat(dashboardPage.profileFullNameIsShown()).isEqualTo(true);
    }
}