package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import pages.ConfirmationModalPage;
import pages.EmployeesRecordPage;
import pages.OrgangeHRMLoginPage;
import pages.TerminationReasonsPage;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static data.UserBuilder.adminUser;

public class PMITerminationReasonsStepDefinitions extends StepDefinitions{

    private OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage(driver);
    private final EmployeesRecordPage employeesRecordPage = new EmployeesRecordPage(driver);
    private final TerminationReasonsPage terminationReasonsPage = new TerminationReasonsPage(driver);
    private final ConfirmationModalPage confirmationModalPage = new ConfirmationModalPage(driver);

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
        assertThat(terminationReasonsPage.numberOfTerminationReasonsRecords()).isGreaterThan(0);
    }

    @Given("I want to add new termination reasons")
    public void goToTerminationReasonsList() {
        iWantToLookForTheAvailableTerminationReasons();
        iGoToPIMConfiguration();
    }

    @When("I fill out the new termination reason form")
    public void iFillOutTheNewTerminationReasonForm() {
        newTerminationReason = "new termination reason " + LocalDateTime.now();
        terminationReasonsPage.clickOnAdd().then().fillOutTerminationReasonFormWith(newTerminationReason)
                .then().clickOnSave();
    }

    @Then("I should be able to see the new termination reason at termination reasons list")
    public void iShouldBeAbleToSeeTheNewTerminationReasonAtTerminationReasonsList() {
        List<WebElement> records = terminationReasonsPage.recordsFoundByTerminationReasonName(newTerminationReason);
        assertThat(records.size()).isEqualTo(1);
    }

    @Given("I want to edit a termination reason")
    public void iWantToEditATerminationReason() {
        goToTerminationReasonsList();
    }

    @Given("I want to delete a termination reason")
    public void iWantToDeleteATerminationReason() {
        goToTerminationReasonsList();
    }

    @Given("I want to delete termination records using bulk actions")
    public void iWantToDeleteTerminationRecordsUsingBulkActions() {
        goToTerminationReasonsList();
    }

    @When("I select all elements on the list to be deleted")
    public void iSelectAllElementsOnTheListToBeDeleted() {
        terminationReasonsPage.clickOnSelectAll().then().clickOnDeleteSelected();
    }

    @Then("confirmation modal should be shown")
    public void confirmationModalShouldBeShown() {
        assertThat(confirmationModalPage.confirmationModalWasShown()).isEqualTo(true);
    }

    @When("I edit the termination reason name")
    public void iEditTheTerminationReasonName() {
        newTerminationReason = "edited termination reason " + LocalDateTime.now();
        terminationReasonsPage.gotToEditFirstTerminationReasonRecord()
                .then().fillOutTerminationReasonFormWith(newTerminationReason).then().clickOnSave();
    }

    @Then("it should be displayed")
    public void itShouldBeDisplayed() {
        iShouldBeAbleToSeeTheNewTerminationReasonAtTerminationReasonsList();
    }

    @When("I delete a termination reason")
    public void iDeleteATerminationReason() {
        String deletedTerminationReason = terminationReasonsPage.getFirstTeminationReason();
        terminationReasonsPage.gotToDeleteFirstTerminationReasonRecord();
        confirmationModalPage.clickOnConfirmDeletion();
    }

    @Then("it shouldn't be displayed")
    public void itShouldnTBeDisplayed() {
        List<WebElement> records = terminationReasonsPage.recordsFoundByTerminationReasonName(newTerminationReason);
        assertThat(records.size()).isEqualTo(0);
    }
}
