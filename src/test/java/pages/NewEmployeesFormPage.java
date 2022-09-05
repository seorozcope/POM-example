package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewEmployeesFormPage extends BasePage{

    @FindBy(name="firstName")
    WebElement inputFirstName;

    @FindBy(name="lastName")
    WebElement inputLastName;

    @FindBy(xpath="//button[@type='submit' and text()=' Save ']")
    WebElement btnSave;


    public NewEmployeesFormPage(WebDriver driver) {
        super(driver);
    }

    public NewEmployeesFormPage enterTheFirstName (String firstName){
        wait.until(ExpectedConditions.elementToBeClickable(inputFirstName));
        inputFirstName.sendKeys(firstName);
        return this;
    }

    public NewEmployeesFormPage enterTheLastName (String lastName){
        inputLastName.sendKeys(lastName);
        return this;
    }

    public NewEmployeesFormPage then (){
        return this;
    }

    public NewEmployeesFormPage clickOnSave() {
        btnSave.click();
        return this;
    }
}
