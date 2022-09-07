package pages;

import data.Employee;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

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
    @FindBy(xpath = "//button[text()=' Yes, Delete ']")
    WebElement btnConfirmDeletion;
    @FindBy(xpath = "//span[text()='No Records Found']")
    WebElement lblNoRecordFound;
    @FindBy(xpath = "//div[contains(@class,'orangehrm-employee-list')]/div[@class='oxd-table-body']/div/div/div/div")
    List<WebElement> lblListRecordFound;


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
    public EmployeesRecordPage clickOnConfirmUserDeletion() {
        btnConfirmDeletion.click();
        return this;
    }

    public boolean noRecordsFoundMessageIsVisible() {
        return lblNoRecordFound.isDisplayed();
    }

    public boolean recordsFoundByUsingId(String id) {
        List<WebElement> result = lblListRecordFound.stream()
                .filter(item -> item.getText().equals(id)).toList();
        return result.size() > 0;
    }
}
