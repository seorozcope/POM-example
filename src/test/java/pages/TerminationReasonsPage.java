package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static utils.actions.ClearField.clearInput;

public class TerminationReasonsPage extends BasePage {
    private final NavigatorPage navigatorPage = new NavigatorPage(driver);

    @FindBy(xpath = "//div[@class='oxd-table-card']/div/div[2]/div")
    List<WebElement> cardTerminationReasonsList;
    @FindBy(xpath = "//i[contains(@class,'bi-pencil-fill')]")
    List<WebElement> btnEditTerminationReasonList;
    @FindBy(xpath = "//i[contains(@class,'bi-trash')]")
    List<WebElement> btnDeleteTerminationReasonList;
    @FindBy(xpath = "//button[text()=' Add ']")
    WebElement btnAdd;
    @FindBy(xpath = "//button[text()=' Save ']")
    WebElement btnSave;
    @FindBy(xpath = "//form[@class='oxd-form']/div/div/div/input[contains(@class,'oxd-input')]")
    WebElement inputName;
    @FindBy(xpath = "//div[contains(@class,'oxd-table-row')]/div/div[@class='oxd-checkbox-wrapper']")
    WebElement checkSelectAll;
    @FindBy(xpath = "//button[text()=' Delete Selected ']")
    WebElement btnDeleteSelected;

    public TerminationReasonsPage(WebDriver driver) {
        super(driver);
    }

    public int numberOfTerminationReasonsRecords() {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(cardTerminationReasonsList.get(0)));
        return cardTerminationReasonsList.size();
    }

    public TerminationReasonsPage clickOnAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAdd));
        btnAdd.click();
        return this;
    }

    public TerminationReasonsPage then() {
        return this;
    }

    public TerminationReasonsPage fillOutTerminationReasonFormWith(String newTerminationReason) {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(inputName));
        clearInput(inputName);
        inputName.sendKeys(newTerminationReason);
        return this;
    }

    public TerminationReasonsPage clickOnSave() {
        btnSave.click();
        navigatorPage.waitUntilSpinnerIsOut();
        return this;
    }

    public List<WebElement> recordsFoundByTerminationReasonName(String newTerminationReason) {
        navigatorPage.waitUntilSpinnerIsOut();
        return cardTerminationReasonsList.stream()
                .filter(item -> item.getText().equals(newTerminationReason)).toList();
    }

    public TerminationReasonsPage clickOnSelectAll() {
        navigatorPage.waitUntilSpinnerIsOut();
        checkSelectAll.click();
        return this;
    }

    public TerminationReasonsPage clickOnDeleteSelected() {
        wait.until(ExpectedConditions.elementToBeClickable(btnDeleteSelected));
        btnDeleteSelected.click();
        return this;
    }

    public TerminationReasonsPage gotToEditFirstTerminationReasonRecord() {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(btnEditTerminationReasonList.get(0)));
        btnEditTerminationReasonList.get(0).click();
        return this;
    }

    public TerminationReasonsPage gotToDeleteFirstTerminationReasonRecord() {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(btnDeleteTerminationReasonList.get(0)));
        btnDeleteTerminationReasonList.get(0).click();
        return this;
    }

    public String getFirstTeminationReason() {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(cardTerminationReasonsList.get(0)));
        return cardTerminationReasonsList.get(0).getText();
    }
}
