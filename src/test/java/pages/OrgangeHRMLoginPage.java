package pages;

import org.openqa.selenium.By;

import static stepdefinitions.Hooks.getDriver;

public class OrgangeHRMLoginPage {

    public By inputUserName = By.name("username");
    public By inputPassword = By.name("password");
    public By btnLogin = By.name("//button[text()=' Login ']");


    public OrgangeHRMLoginPage enterTheUsername (String username){
        getDriver().findElement(inputUserName).sendKeys(username);
        return this;
    }

    public OrgangeHRMLoginPage enterThePassword (String password){
        getDriver().findElement(inputPassword).sendKeys(password);
        return this;
    }

    public OrgangeHRMLoginPage clickOnLogin (){
        getDriver().findElement(btnLogin).click();
        return this;
    }
}
