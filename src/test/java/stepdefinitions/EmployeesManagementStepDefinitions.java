package stepdefinitions;

import data.Employee;
import data.EmployeeBuilder;
import data.User;
import data.UserBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.*;

import static data.UserBuilder.adminUser;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeesManagementStepDefinitions extends StepDefinitions {

    private final NavigatorPage navigatorPage = new NavigatorPage(driver);
    private final OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage(driver);
    private final DashboardPage dashboardPage = new DashboardPage(driver);
    private final EmployeesRecordPage employeesRecordPage = new EmployeesRecordPage(driver);
    private final NewEmployeesFormPage newEmployeesFormPage = new NewEmployeesFormPage(driver);
    private final EmployeeProfilePage employeeProfilePage = new EmployeeProfilePage(driver);

    private final Employee employee = new EmployeeBuilder().setFirstName("Sebastian").setLastName("Orozco Peña").build();
    private User user;

    @Given("^I want to register an employee$")
    public void iWantToRegisterAnEmployee() {
        loginPage.loginWithCredentials(adminUser());
        navigatorPage.clickOnPMI();
    }

    @When("^I submit the new employee form filling out the required fields$")
    public void submitTheNewEmployeeFormFillingOutTheRequiredFields() {
        employeesRecordPage.clickOnAddEmployee();
        navigatorPage.waitUntilSpinnerIsOut();
        employee.setId(newEmployeesFormPage.getEmployeeID());
        newEmployeesFormPage.enterTheFirstName(employee.getFirstName()).then().enterTheLastName(employee.getLastName()).then().clickOnSave();
    }

    @When("^I submit the new employee with signing account form filling out the required fields$")
    public void submitTheNewEmployeeWithSigningAccountFormFillingOutTheRequiredFields() {
        employeesRecordPage.clickOnAddEmployee();
        navigatorPage.waitUntilSpinnerIsOut();
        employee.setId(newEmployeesFormPage.getEmployeeID());
        String username = employee.getFirstName().concat(employee.getId());
        user = new UserBuilder().setUsername(username).setPassword("@".concat(username)).build();
        newEmployeesFormPage.fillOutEmployeeInfo(employee).withLoginCredentials(user)
                .then().markAccountAsEnabled().then().clickOnSave();
    }

    @When("^I submit the new employee creating a disabled account form filling out the required fields$")
    public void submitTheNewEmployeeCreatingADisabledAccountFormFillingOutTheRequiredFields() {
        employeesRecordPage.clickOnAddEmployee();
        navigatorPage.waitUntilSpinnerIsOut();
        String username = employee.getFirstName().concat(newEmployeesFormPage.getEmployeeID());
        user = new UserBuilder().setUsername(username).setPassword("@".concat(username)).build();
        newEmployeesFormPage.fillOutEmployeeInfo(employee).withLoginCredentials(user)
                .then().markAccountAsDisabled().then().clickOnSave();
    }

    @Then("^the new employee should be registered$")
    public void theNewEmployeeShouldBeRegistered() {
        navigatorPage.waitUntilSpinnerIsOut();
        assertThat(employeeProfilePage.fullNameShown()).isEqualTo(employee.getFullName());
        assertThat(employeeProfilePage.firstNameDefaultValueShown()).isEqualTo(employee.getFirstName());
        assertThat(employeeProfilePage.lastNameDefaultValueShown()).isEqualTo(employee.getLastName());
    }

    @And("^should have been granted with access to OrangeHRM$")
    public void shouldHaveBeenGrantedWithAccessToOrangeHRM() {
        dashboardPage.clickOnProfileFullName().then().clickOnLogout();
        loginPage.loginWithCredentials(user);
        assertThat(dashboardPage.profilePictureIsShown()).isEqualTo(true);
        assertThat(dashboardPage.profileFullNameIsShown()).isEqualTo(true);
        assertThat(dashboardPage.profileFullNameShowed()).isEqualTo(employee.getFullName());

    }

    @And("^should see (.*) when tried to login$")
    public void shouldNotBeenGrantedWithAccessToOrangeHRM(String expectedErrorMessage) {
        dashboardPage.clickOnProfileFullName().then().clickOnLogout();
        loginPage.loginWithCredentials(user);
        assertThat(loginPage.getShowedErrorMessage()).isEqualTo(expectedErrorMessage);
    }

    @Given("^I have an employee account registered$")
    public void iHaveAnEmployeeAccountRegistered() {
        loginPage.loginWithCredentials(adminUser());
        navigatorPage.clickOnPMI();
        submitTheNewEmployeeFormFillingOutTheRequiredFields();
        navigatorPage.waitUntilSpinnerIsOut();

    }

    @When("^an admin changes the account details fullname and lastname$")
    public void andAdminChangesTheAccountDetailsFullnameAndLastname() {
        employee.setFirstName("EditedFirstname");
        employee.setLastName("EditedLastname");
        employeesRecordPage.clickOnEmployeeList().then().searchByEmployeeId(employee).clickOnEditEmployee();
        navigatorPage.waitUntilSpinnerIsOut();
        newEmployeesFormPage.enterTheFirstName(employee.getFirstName()).then().enterTheLastName(employee.getLastName())
                .then().clickOnSave();
        navigatorPage.waitUntilSpinnerIsOut();
    }

    @Then("should see the new account details")
    public void shouldSeeTheNewAccountDetails() {
        theNewEmployeeShouldBeRegistered();
    }
}
