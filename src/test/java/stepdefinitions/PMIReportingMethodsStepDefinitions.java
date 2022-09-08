package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.ConfirmationModalPage;
import pages.EmployeesRecordPage;
import pages.ListOfConfiguredMethodsPage;
import pages.OrgangeHRMLoginPage;

import java.time.LocalDateTime;
import java.util.List;

import static data.UserBuilder.adminUser;
import static org.assertj.core.api.Assertions.assertThat;

public class PMIReportingMethodsStepDefinitions extends StepDefinitions {

    private final EmployeesRecordPage employeesRecordPage = new EmployeesRecordPage(driver);
    private final ListOfConfiguredMethodsPage listOfConfiguredMethodsPage = new ListOfConfiguredMethodsPage(driver);
    private final ConfirmationModalPage confirmationModalPage = new ConfirmationModalPage(driver);
    private OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage(driver);
    private String newReportingMethod;

    @Given("I want to look for the available reporting methods")
    public void iWantToLookForTheAvailableReportingMethods() {
        loginPage.loginWithCredentials(adminUser());
    }

    @When("I go to PIM configuration reporting methods")
    public void iGoToPIMConfigurationReportingMethods() {
        employeesRecordPage.clickOnConfigurarion().then().clickOnReportingMethods();
    }

    @Then("I should be able to see the list of all available reporting methods")
    public void iShouldBeAbleToSeeTheListOfAllAvailableReportingMethods() {
        assertThat(listOfConfiguredMethodsPage.numberOfCardElementsRecords()).isGreaterThan(0);
    }

    @Given("I want to add new reporting method")
    public void goToReportingMethods() {
        iWantToLookForTheAvailableReportingMethods();
        iGoToPIMConfigurationReportingMethods();
    }

    @When("I fill out the new reporting method form")
    public void iFillOutTheNewReportingMethodForm() {
        newReportingMethod = "new reporting method " + LocalDateTime.now();
        listOfConfiguredMethodsPage.clickOnAdd().then().fillOutNewElementFormWith(newReportingMethod)
                .then().clickOnSave();
    }

    @Then("I should be able to see the new reporting method at list")
    public void iShouldBeAbleToSeeTheNewReportingMethodAtList() {
        List<WebElement> records = listOfConfiguredMethodsPage.recordsFoundByCardElementName(newReportingMethod);
        assertThat(records.size()).isEqualTo(1);
    }

    @Given("I want to edit a reporting method")
    public void iWantToEditAReportingMethod() {
        goToReportingMethods();
    }

    @When("I edit the reporting method name")
    public void iEditTheReportingMethodName() {
        newReportingMethod = "edited termination reason " + LocalDateTime.now();
        listOfConfiguredMethodsPage.gotToEditFirstCardElementRecord()
                .then().fillOutNewElementFormWith(newReportingMethod).then().clickOnSave();
    }

    @Then("it should be displayed at reporting method list")
    public void itShouldBeDisplayedAtReportingMethodList() {
        iShouldBeAbleToSeeTheNewReportingMethodAtList();
    }

    @Given("I want to delete a reporting method")
    public void iWantToDeleteAReportingMethod() {
        goToReportingMethods();
        iFillOutTheNewReportingMethodForm();
    }

    @When("I delete a reporting method")
    public void iDeleteAReportingMethod() {
        listOfConfiguredMethodsPage.gotToDeleteTheElementRecordAs(newReportingMethod);
        confirmationModalPage.clickOnConfirmDeletion();
    }

    @Then("it shouldn't be displayed anymore ate reporting method list")
    public void itShouldnTBeDisplayedAnymoreAteReportingMethodList() {
        List<WebElement> records = listOfConfiguredMethodsPage.recordsFoundByCardElementName(newReportingMethod);
        assertThat(records.size()).isEqualTo(0);
    }

    @Given("I want to delete reporting methods using bulk actions")
    public void iWantToDeleteReportingMethodsUsingBulkActions() {
        goToReportingMethods();
    }

    @When("I select all elements on the reporting methods list to be deleted")
    public void iSelectAllElementsOnTheReportingMethodsListToBeDeleted() {
        iFillOutTheNewReportingMethodForm();
        listOfConfiguredMethodsPage.clickOnSelectAll().then().clickOnDeleteSelected();
    }
}
