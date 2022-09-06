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

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeesManagementStepDefinitions extends StepDefinitions {

    private final NavigatorPage navigatorPage = new NavigatorPage(driver);
    private final Employee employee = new EmployeeBuilder().setFirstName("Sebastian").setLastName("Orozco Peña").build();
    private User user;

    @Given("^I want to register an employee$")
    public void iWantToRegisterAnEmployee() {
        navigatorPage.clickOnPMI();
    }

    @When("^I submit the new employee form filling out the required fields$")
    public void submitTheNewEmployeeFormFillingOutTheRequiredFields() {
        EmployeesRecordPage employeesRecordPage = new EmployeesRecordPage(driver);
        NewEmployeesFormPage newEmployeesFormPage = new NewEmployeesFormPage(driver);
        employeesRecordPage.clickOnAddUser();
        navigatorPage.waitUntilSpinnerIsOut();
        newEmployeesFormPage.enterTheFirstName(employee.getFirstName()).then().enterTheLastName(employee.getLastName()).then().clickOnSave();
    }

    @When("^I submit the new employee with signing account form filling out the required fields$")
    public void submitTheNewEmployeeWithSigningAccountFormFillingOutTheRequiredFields() {
        EmployeesRecordPage employeesRecordPage = new EmployeesRecordPage(driver);
        NewEmployeesFormPage newEmployeesFormPage = new NewEmployeesFormPage(driver);
        employeesRecordPage.clickOnAddUser();
        navigatorPage.waitUntilSpinnerIsOut();
        String username = employee.getFirstName().concat(newEmployeesFormPage.getEmployeeID());
        user = new UserBuilder().setUsername(username).setPassword("@".concat(username)).build();
        newEmployeesFormPage.enterTheFirstName(employee.getFirstName()).then().enterTheLastName(employee.getLastName())
                .then().clickOnCreateLoginDetailsToggle().then().enterTheUsername(user.getUsername())
                .then().enterThePassword(user.getPassword()).then().confirmThePassword(user.getPassword())
                .then().markAccountAsEnabled().then().clickOnSave();
    }

    @Then("^the new employee should be registered$")
    public void theNewEmployeeShouldBeRegistered() {
        EmployeeProfilePage employeeProfilePage = new EmployeeProfilePage(driver);
        navigatorPage.waitUntilSpinnerIsOut();
        assertThat(employeeProfilePage.fullNameShown()).isEqualTo(employee.getFullName());
        assertThat(employeeProfilePage.firstNameDefaultValueShown()).isEqualTo(employee.getFirstName());
        assertThat(employeeProfilePage.lastNameDefaultValueShown()).isEqualTo(employee.getLastName());
    }

    @And("^should have been granted with access to OrangeHRM$")
    public void shouldHaveBeenGrantedWithAccessToOrangeHRM() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickOnProfileFullName().then().clickOnLogout();
        OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage(driver);
        loginPage.loginWithCredentials(user);
        assertThat(dashboardPage.profilePictureIsShown()).isEqualTo(true);
        assertThat(dashboardPage.profileFullNameIsShown()).isEqualTo(true);
        assertThat(dashboardPage.profileFullNameShowed()).isEqualTo(employee.getFullName());

    }
}
