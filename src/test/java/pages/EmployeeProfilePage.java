package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class EmployeeProfilePage extends BasePage {

    @FindBy(xpath = "//div[@class='orangehrm-edit-employee-name']/h6")
    WebElement lblEmployeeFullName;
    @FindBy(name = "firstName")
    WebElement inputFirstName;
    @FindBy(name = "lastName")
    WebElement inputlastName;


    public EmployeeProfilePage(WebDriver driver) {
        super(driver);
    }

    public String fullNameShown() {
        wait.until(ExpectedConditions.visibilityOf(lblEmployeeFullName));
        return lblEmployeeFullName.getText();
    }

    public String firstNameDefaultValueShown() {
        return inputFirstName.getAttribute("value");
    }

    public String lastNameDefaultValueShown() {
        return inputlastName.getAttribute("value");
    }
}
