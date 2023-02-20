package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private static final Logger LOGGER = LogManager.getLogger();

//
//    By userNameField = By.id("inputEmail");
//    By passwordField = By.id("inputPassword");
//    By signInButton = By.xpath("//button[contains(text(),'Sign in')]");
//
//    public LoginPage enterUserName(String userName) {
//        LOGGER.info("Enter user name {} in the userName field", userName);
//        getElement(userNameField).sendKeys(userName);
//        return this;
//    }
//
//    public LoginPage enterPassword(String password) {
//        LOGGER.info("Enter password {} in the password field", password);
//        getElement(passwordField).sendKeys(password);
//        return this;
//    }
//
//    public LoginPage clickSignInButton() {
//        LOGGER.info("Click on signInButton");
//        getElement(signInButton).click();
//        return this;
//    }

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
        LOGGER.info("Enter user name {} in the userName field", userName);
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage enterPassword(String password) {
        LOGGER.info("Enter password {} in the password field", password);
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickSignInButton() {
        LOGGER.info("Click on signInButton");
        signInButton.click();
        return this;
    }
}
