package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//img[@alt='profile picture']")
    WebElement imgProfilePicture;
    @FindBy(className = "oxd-userdropdown-name")
    WebElement lblProfileFullName;
    @FindBy(xpath = "//a[text()='Logout']")
    WebElement btnLogout;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean profilePictureIsShown() {
        wait.until(ExpectedConditions.elementToBeClickable(imgProfilePicture));
        return imgProfilePicture.isDisplayed();
    }

    public boolean profileFullNameIsShown() {
        wait.until(ExpectedConditions.elementToBeClickable(lblProfileFullName));
        return lblProfileFullName.isDisplayed();
    }

    public DashboardPage clickOnProfileFullName() {
        lblProfileFullName.click();
        return this;
    }

    public DashboardPage then() {
        return this;
    }

    public DashboardPage clickOnLogout() {
        btnLogout.click();
        return this;
    }

    public String profileFullNameShowed() {
        return lblProfileFullName.getText();
    }
}
