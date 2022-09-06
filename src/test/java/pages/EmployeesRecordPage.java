package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmployeesRecordPage extends BasePage {

    @FindBy(xpath = "//a[text()='Add Employee']")
    WebElement btnAddEmployee;


    public EmployeesRecordPage(WebDriver driver) {
        super(driver);
    }

    public EmployeesRecordPage clickOnAddUser() {
        wait.until(ExpectedConditions.elementToBeClickable(btnAddEmployee));
        btnAddEmployee.click();
        return this;
    }
}
