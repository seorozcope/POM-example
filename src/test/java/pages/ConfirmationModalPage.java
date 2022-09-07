package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ConfirmationModalPage extends BasePage {

    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    WebElement btnConfirmDeletion;

    public ConfirmationModalPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmationModalPage clickOnConfirmDeletion() {
        btnConfirmDeletion.click();
        return this;
    }

    public boolean confirmationModalWasShown() {
        wait.until(ExpectedConditions.elementToBeClickable(btnConfirmDeletion));
        return btnConfirmDeletion.isDisplayed();
    }
}
