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

public class PMITerminationReasonsStepDefinitions extends StepDefinitions {

    private final EmployeesRecordPage employeesRecordPage = new EmployeesRecordPage(driver);
    private final ListOfConfiguredMethodsPage listOfConfiguredMethodsPage = new ListOfConfiguredMethodsPage(driver);
    private final ConfirmationModalPage confirmationModalPage = new ConfirmationModalPage(driver);
    private OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage(driver);
    private String newTerminationReason;

    @Given("^I want to look for the available termination reasons$")
    public void iWantToLookForTheAvailableTerminationReasons() {
        loginPage.loginWithCredentials(adminUser());
    }

    @When("^I go to PIM configuration$")
    public void iGoToPIMConfiguration() {
        employeesRecordPage.clickOnConfigurarion().then().clickOnTerminationReasons();
    }

    @Then("^I should be able to see the list of all available termination reasons$")
    public void iShouldBeAbleToSeeTheListOfAllAvailableTerminationReasons() {
        assertThat(listOfConfiguredMethodsPage.numberOfCardElementsRecords()).isGreaterThan(0);
    }

    @Given("I want to add new termination reasons")
    public void goToTerminationReasonsList() {
        iWantToLookForTheAvailableTerminationReasons();
        iGoToPIMConfiguration();
    }

    @When("I fill out the new termination reason form")
    public void iFillOutTheNewTerminationReasonForm() {
        newTerminationReason = "new termination reason " + LocalDateTime.now();
        listOfConfiguredMethodsPage.clickOnAdd().then().fillOutNewElementFormWith(newTerminationReason)
                .then().clickOnSave();
    }

    @Then("I should be able to see the new termination reason at termination reasons list")
    public void iShouldBeAbleToSeeTheNewTerminationReasonAtTerminationReasonsList() {
        List<WebElement> records = listOfConfiguredMethodsPage.recordsFoundByCardElementName(newTerminationReason);
        assertThat(records.size()).isEqualTo(1);
    }

    @Given("I want to edit a termination reason")
    public void iWantToEditATerminationReason() {
        goToTerminationReasonsList();
    }

    @Given("I want to delete a termination reason")
    public void iWantToDeleteATerminationReason() {
        goToTerminationReasonsList();
        iFillOutTheNewTerminationReasonForm();
    }

    @Given("I want to delete termination records using bulk actions")
    public void iWantToDeleteTerminationRecordsUsingBulkActions() {
        goToTerminationReasonsList();
    }

    @When("I select all elements on the list to be deleted")
    public void iSelectAllElementsOnTheListToBeDeleted() {
        iFillOutTheNewTerminationReasonForm();
        listOfConfiguredMethodsPage.clickOnSelectAll().then().clickOnDeleteSelected();
    }

    @Then("confirmation modal should be shown")
    public void confirmationModalShouldBeShown() {
        assertThat(confirmationModalPage.confirmationModalWasShown()).isEqualTo(true);
    }

    @When("I edit the termination reason name")
    public void iEditTheTerminationReasonName() {
        newTerminationReason = "edited termination reason " + LocalDateTime.now();
        listOfConfiguredMethodsPage.gotToEditFirstCardElementRecord()
                .then().fillOutNewElementFormWith(newTerminationReason).then().clickOnSave();
    }

    @Then("it should be displayed")
    public void itShouldBeDisplayed() {
        iShouldBeAbleToSeeTheNewTerminationReasonAtTerminationReasonsList();
    }

    @When("I delete a termination reason")
    public void iDeleteATerminationReason() {
        listOfConfiguredMethodsPage.gotToDeleteTheElementRecordAs(newTerminationReason);
        confirmationModalPage.clickOnConfirmDeletion();
    }

    @Then("it shouldn't be displayed")
    public void itShouldnTBeDisplayed() {
        List<WebElement> records = listOfConfiguredMethodsPage.recordsFoundByCardElementName(newTerminationReason);
        assertThat(records.size()).isEqualTo(0);
    }
}
