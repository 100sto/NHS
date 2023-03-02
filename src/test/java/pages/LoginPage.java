package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;
import utils.DriverHelper;

public class LoginPage extends BrowserUtils {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "inputEmail")
    private WebElement userNameField;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Sign in')]")
    private WebElement signInButton;

    public LoginPage enterUserName(String userName) {
        LOGGER.info("Enter user name '{}' in the userName field", userName);
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        LOGGER.info("Enter password ***** in the password field");
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSignInButton() {
        LOGGER.info("Click on signInButton");
        signInButton.click();
        return this;
    }

    public LoginPage verifyLoginPageTitle() {
        LOGGER.info("Verify the login page title");
        Assert.assertEquals(DriverHelper.getDriver().getTitle(), "LoginPage");
        return this;
    }
}
