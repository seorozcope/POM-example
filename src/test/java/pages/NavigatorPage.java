package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class NavigatorPage extends BasePage {

    @FindBy(xpath = "//ul[@class='oxd-main-menu']/child::li/child::a/child::span[text()='PIM']")
    WebElement btnPMI;
    @FindBy(className = "oxd-loading-spinner")
    WebElement loadingSpinner;
    @FindBy(xpath = "//div[@class='oxd-main-menu-search']/input")
    WebElement inputSearch;
    @FindBy(xpath = "//li[@class='oxd-main-menu-item-wrapper']/a/span")
    List<WebElement> optionsList;


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

    public NavigatorPage searchForAnOption(String optionName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputSearch));
        inputSearch.sendKeys(optionName);
        return this;
    }

    public int getOptionsCount() {
        return optionsList.size();
    }

    public int countOptionsWithText(String text) {
        return optionsList.stream()
                .filter(item -> item.getText().contains(text)).toList().size();
    }

}
