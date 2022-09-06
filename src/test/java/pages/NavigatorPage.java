package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavigatorPage extends BasePage {

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/child::li/child::a/child::span[text()='PIM']")
    WebElement btnPMI;
    @FindBy(className = "oxd-loading-spinner")
    WebElement loadingSpinner;


    public NavigatorPage(WebDriver driver) {
        super(driver);
    }

    public NavigatorPage clickOnPMI() {
        wait.until(ExpectedConditions.elementToBeClickable(btnPMI));
        btnPMI.click();
        return this;
    }

    public NavigatorPage waitUntilSpinnerIsOut() {
        wait.until(ExpectedConditions.invisibilityOf(loadingSpinner));
        return this;
    }

}
