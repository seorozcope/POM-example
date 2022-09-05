package stepdefinitions;

import data.Employee;
import data.EmployeeBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.EmployeeProfilePage;
import pages.EmployeesRecordPage;
import pages.NavigatorPage;
import pages.NewEmployeesFormPage;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeesManagementStepDefinitions extends StepDefinitions{

    private final NavigatorPage navigatorPage = new NavigatorPage(driver);

    private final Employee employee= new EmployeeBuilder().setFirstName("Sebastian").setLastName("Orozco Peña").build();
    @Given("^I want to register an employee$")
    public void iWantToRegisterAnEmployee() {
        navigatorPage.clickOnPMI();
    }
    @When("^I submit the new employee form filling out the required fields$")
    public void submitTheNewEmployeeFormFillingOutTheRequiredFields() {
        EmployeesRecordPage employeesRecordPage = new EmployeesRecordPage(driver);
        NewEmployeesFormPage newEmployeesFormPage= new NewEmployeesFormPage(driver);
        employeesRecordPage.clickOnAddUser();
        navigatorPage.waitUntilSpinnerIsOut();
        newEmployeesFormPage.enterTheFirstName(employee.getFirstName()).then().enterTheLastName(employee.getLastName()).then().clickOnSave();
    }
    @Then("^the new employee should be registered$")
    public void theNewEmployeeShouldBeRegistered() {
        EmployeeProfilePage employeeProfilePage = new EmployeeProfilePage(driver);
        navigatorPage.waitUntilSpinnerIsOut();
        assertThat(employeeProfilePage.fullNameShown()).isEqualTo(employee.getFullName());
        assertThat(employeeProfilePage.firstNameDefaultValueShown()).isEqualTo(employee.getFirstName());
        assertThat(employeeProfilePage.lastNameDefaultValueShown()).isEqualTo(employee.getLastName());
    }
}
