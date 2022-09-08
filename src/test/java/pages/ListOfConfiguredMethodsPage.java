package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.IntStream;

import static utils.actions.ClearField.clearInput;

public class ListOfConfiguredMethodsPage extends BasePage {
    private final NavigatorPage navigatorPage = new NavigatorPage(driver);

    @FindBy(xpath = "//div[@class='oxd-table-card']/div/div[2]/div")
    List<WebElement> cardElementsList;
    @FindBy(xpath = "//i[contains(@class,'bi-pencil-fill')]")
    List<WebElement> btnEditCardElementList;
    @FindBy(xpath = "//i[contains(@class,'bi-trash')]")
    List<WebElement> btnDeleteCardElementList;
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

    public ListOfConfiguredMethodsPage(WebDriver driver) {
        super(driver);
    }

    public int numberOfCardElementsRecords() {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(cardElementsList.get(0)));
        return cardElementsList.size();
    }

    public ListOfConfiguredMethodsPage clickOnAdd() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAdd));
        btnAdd.click();
        return this;
    }

    public ListOfConfiguredMethodsPage then() {
        return this;
    }

    public ListOfConfiguredMethodsPage fillOutNewElementFormWith(String newElementName) {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(inputName));
        clearInput(inputName);
        inputName.sendKeys(newElementName);
        return this;
    }

    public ListOfConfiguredMethodsPage clickOnSave() {
        btnSave.click();
        navigatorPage.waitUntilSpinnerIsOut();
        return this;
    }

    public List<WebElement> recordsFoundByCardElementName(String elementDescription) {
        navigatorPage.waitUntilSpinnerIsOut();
        return cardElementsList.stream()
                .filter(item -> item.getText().equals(elementDescription)).toList();
    }

    public ListOfConfiguredMethodsPage clickOnSelectAll() {
        navigatorPage.waitUntilSpinnerIsOut();
        checkSelectAll.click();
        return this;
    }

    public ListOfConfiguredMethodsPage clickOnDeleteSelected() {
        wait.until(ExpectedConditions.elementToBeClickable(btnDeleteSelected));
        btnDeleteSelected.click();
        return this;
    }

    public ListOfConfiguredMethodsPage gotToEditFirstCardElementRecord() {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(btnEditCardElementList.get(0)));
        btnEditCardElementList.get(0).click();
        return this;
    }

    public int getCardElementIndexByDescription(String elementDescription) {
        return IntStream.range(0, cardElementsList.size())
                .filter(i -> cardElementsList.get(i).getText().equals(elementDescription))
                .findFirst()
                .orElse(-1);
    }

    public ListOfConfiguredMethodsPage gotToDeleteTheElementRecordAs(String elementDescription) {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(btnDeleteCardElementList.get(0)));
        btnDeleteCardElementList.get(getCardElementIndexByDescription(elementDescription)).click();
        return this;
    }

    public String getFirstCardElementDescription() {
        navigatorPage.waitUntilSpinnerIsOut();
        wait.until(ExpectedConditions.elementToBeClickable(cardElementsList.get(0)));
        return cardElementsList.get(0).getText();
    }
}
