package pages;

import data.Employee;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmployeesRecordPage extends BasePage {

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement btnAddEmployee;
    @FindBy(xpath = "//a[text()='Employee List']")
    WebElement btnEmployeeList;
    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/parent::div/child::div[2]/input")
    WebElement inputEmployeeId;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnSubmit;
    @FindBy(xpath = "//i[contains(@class,'bi-pencil-fill')]/parent::button")
    WebElement btnEdit;
    @FindBy(xpath = "//i[contains(@class,'bi-trash')]/parent::button")
    WebElement btnDelete;


    public EmployeesRecordPage(WebDriver driver) {
        super(driver);
    }

    public EmployeesRecordPage clickOnAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAddEmployee));
        btnAddEmployee.click();
        return this;
    }

    public EmployeesRecordPage clickOnEmployeeList() {
        wait.until(ExpectedConditions.elementToBeClickable(btnEmployeeList));
        btnEmployeeList.click();
        return this;
    }

    public EmployeesRecordPage then() {
        return this;
    }

    public EmployeesRecordPage searchByEmployeeId(Employee employee) {
        NavigatorPage navigatorPage = new NavigatorPage(driver);
        navigatorPage.waitUntilSpinnerIsOut();
        inputEmployeeId.sendKeys(employee.getId());
        btnSubmit.click();
        navigatorPage.waitUntilSpinnerIsOut();
        return this;
    }

    public EmployeesRecordPage clickOnEditEmployee() {
        btnEdit.click();
        return this;
    }

    public EmployeesRecordPage clickOnDeleteEmployee() {
        btnDelete.click();
        return this;
    }
}
