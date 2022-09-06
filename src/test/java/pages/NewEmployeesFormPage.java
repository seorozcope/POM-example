package pages;

import data.Employee;
import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewEmployeesFormPage extends BasePage {

    @FindBy(name = "firstName")
    WebElement inputFirstName;

    @FindBy(name = "lastName")
    WebElement inputLastName;
    @FindBy(xpath = "//div[@class='orangehrm-employee-form']/div/div[2]/div/div/div[2]/input")
    WebElement inputEmployeeId;

    @FindBy(xpath = "//button[@type='submit' and text()=' Save ']")
    WebElement btnSave;
    @FindBy(xpath = "//div[contains(@class,'user-form-header')]/div[contains(@class,'oxd-switch-wrapper')]/label/span")
    WebElement toggleCreateLoginDetails;
    @FindBy(xpath = "//label[text()='Username']/parent::div/following::div[1]/child::input")
    WebElement inputUsername;

    @FindBy(xpath = "//label[text()='Password']/parent::div/following::div[1]/child::input")
    WebElement inputPassword;

    @FindBy(xpath = "//label[text()='Confirm Password']/parent::div/following::div[1]/child::input")
    WebElement inputConfirmPassword;
    @FindBy(xpath = "//div[@class='--status-grouped-field']/div[1]/div[2]/div/label/span")
    WebElement radioMarkAsEnabled;
    @FindBy(xpath = "//div[@class='--status-grouped-field']/div[2]/div[2]/div/label/span")
    WebElement radioMarkAsDisabled;


    public NewEmployeesFormPage(WebDriver driver) {
        super(driver);
    }

    public NewEmployeesFormPage enterTheFirstName(String firstName) {
        wait.until(ExpectedConditions.elementToBeClickable(inputFirstName));
        inputFirstName.sendKeys(firstName);
        return this;
    }

    public NewEmployeesFormPage enterTheLastName(String lastName) {
        inputLastName.sendKeys(lastName);
        return this;
    }

    public NewEmployeesFormPage then() {
        return this;
    }

    public NewEmployeesFormPage clickOnSave() {
        btnSave.click();
        return this;
    }

    public NewEmployeesFormPage clickOnCreateLoginDetailsToggle() {
        toggleCreateLoginDetails.click();
        return this;
    }

    public String getEmployeeID() {
        return inputEmployeeId.getAttribute("value");
    }

    public NewEmployeesFormPage enterTheUsername(String username) {
        inputUsername.sendKeys(username);
        return this;
    }

    public NewEmployeesFormPage enterThePassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    public NewEmployeesFormPage confirmThePassword(String password) {
        inputConfirmPassword.sendKeys(password);
        return this;
    }

    public NewEmployeesFormPage markAccountAsEnabled() {
        radioMarkAsEnabled.click();
        return this;
    }

    public NewEmployeesFormPage fillOutEmployeeInfo(Employee employee) {
        enterTheFirstName(employee.getFirstName())
                .then().enterTheLastName(employee.getLastName());
        return this;
    }

    public NewEmployeesFormPage fillOutCredentialDetails(User user) {
        enterTheUsername(user.getUsername())
                .then().enterThePassword(user.getPassword())
                .then().confirmThePassword(user.getPassword());
        return this;
    }

    public NewEmployeesFormPage markAccountAsDisabled() {
        radioMarkAsDisabled.click();
        return this;
    }
}
