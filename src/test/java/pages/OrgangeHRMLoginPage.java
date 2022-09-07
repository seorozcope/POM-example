package pages;

import data.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrgangeHRMLoginPage extends BasePage {

    @FindBy(name = "username")
    WebElement inputUserName;
    @FindBy(name = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[text()=' Login ']")
    WebElement btnLogin;
    @FindBy(xpath = "//div[@role='alert']/child::div[1]/child::p[1]")
    WebElement lblErrorMessage;

    public OrgangeHRMLoginPage(WebDriver driver) {
        super(driver);
    }

    public OrgangeHRMLoginPage enterTheUsername(String username) {
        wait.until(ExpectedConditions.elementToBeClickable(inputUserName));
        inputUserName.sendKeys(username);
        return this;
    }

    public OrgangeHRMLoginPage enterThePassword(String password) {
        inputPassword.sendKeys(password);
        return this;
    }

    public OrgangeHRMLoginPage clickOnLogin() {
        btnLogin.click();
        return this;
    }

    public OrgangeHRMLoginPage then() {
        return this;
    }

    public String getShowedErrorMessage() {
        wait.until(ExpectedConditions.elementToBeClickable(inputUserName));
        return lblErrorMessage.getText();
    }

    public void loginWithCredentials(User user) {
        enterTheUsername(user.getUsername())
                .then().enterThePassword(user.getPassword())
                .then().clickOnLogin();
    }

    public boolean loginButtonIsVisible() {
        return btnLogin.isDisplayed();
    }
}
