package stepdefinitions;

import org.openqa.selenium.WebDriver;

import static stepdefinitions.Hooks.getDriver;

public abstract class StepDefinitions {

    protected final WebDriver driver = getDriver();

}
