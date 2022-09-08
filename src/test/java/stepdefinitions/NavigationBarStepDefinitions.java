package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.NavigatorPage;
import pages.OrgangeHRMLoginPage;

import static data.UserBuilder.adminUser;
import static org.assertj.core.api.Assertions.assertThat;

public class NavigationBarStepDefinitions extends StepDefinitions {
    private OrgangeHRMLoginPage loginPage = new OrgangeHRMLoginPage(driver);
    private NavigatorPage navigatorPage = new NavigatorPage(driver);
    private String searchTerm = "Admin";
    private int numberOfOptions;

    @Given("I want to search for an option at navigation bar")
    public void iWantToSearchForAnOptionAtNavigationBar() {
        loginPage.loginWithCredentials(adminUser());
        numberOfOptions = navigatorPage.countOptionsWithText(searchTerm);
    }

    @When("I introduce an existing option on the searchbar")
    public void iIntroduceAnExistingOptionOnTheSearchbar() {
        navigatorPage.searchForAnOption(searchTerm);
    }

    @Then("it should be the only option displayed at navigation list")
    public void itShouldBeTheOnlyOptionDisplayedAtNavigationList() {
        assertThat(navigatorPage.getOptionsCount()).isEqualTo(numberOfOptions);
        assertThat(navigatorPage.getOptionsCount()).isEqualTo(1);
    }

    @When("I introduce a substring for an existing option on the searchbar")
    public void iIntroduceASubstringForAnExistingOptionOnTheSearchbar() {
        searchTerm = "n";
        numberOfOptions = navigatorPage.countOptionsWithText(searchTerm);
        navigatorPage.searchForAnOption(searchTerm);
    }

    @Then("it should show all options that contains the substring")
    public void itShouldShowAllOptionsThatContainsTheSubstring() {
        assertThat(navigatorPage.getOptionsCount()).isEqualTo(numberOfOptions);
    }

    @When("I introduce a non existing option on the searchbar")
    public void iIntroduceANonExistingOptionOnTheSearchbar() {
        searchTerm = "not an option here";
        numberOfOptions = navigatorPage.countOptionsWithText(searchTerm);
        navigatorPage.searchForAnOption(searchTerm);
    }

    @Then("navigation list should be empty")
    public void navigationListShouldBeEmpty() {
        assertThat(navigatorPage.getOptionsCount()).isEqualTo(numberOfOptions);
        assertThat(navigatorPage.getOptionsCount()).isEqualTo(0);
    }
}
