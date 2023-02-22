package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReared;
import utils.DriverHelper;

public class LoginStepDefinitions {
    WebDriver driver = DriverHelper.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = new HomePage(driver);

    @Given("The user lands on NHS portal login page")
    public void theUserIsOnLandedOnNHSPortalLoginPage() {
        driver.navigate().to(ConfigReared.readProperty("nhsURL"));
    }

    @When("The user enters correct credentials")
    public void theUserEntersCorrectCredentials() {
        loginPage.enterUserName(ConfigReared.readProperty("nhsUsername"))
                .enterPassword(ConfigReared.readProperty("nhsPassword"))
                .clickSignInButton();
    }

    @Then("The user is on the NHS home page")
    public void theUserIsOnTheNHSHomePage() {
        homePage.verifyHomePageTitle(Hooks.softAssertions);
    }

    @When("The user enters wrong {string} and {string}")
    public void theUserEntersWrongAnd(String userName, String password) {
        loginPage.enterUserName(userName)
                .enterPassword(password)
                .clickSignInButton();
    }

    @Then("The user is still on the login page")
    public void theUserIsStillOnTheLoginPage() {
        loginPage.verifyLoginPageTitle(Hooks.softAssertions);
    }



//    @Given("The user enters correct credentials")
//    public void theUserEntersCorrectCredentials() {
////        getPage(LoginPage.class)
////        driver.navigate().to(ConfigReared.readProperty("nhsURL"));
//    }
//
//    @When("The user is on landed on NHS portal login page")
//    public void theUserIsOnLandedOnNHSPortalLoginPage() {
//        getPage(LoginPage.class)
//                .enterUserName("Admin")
//                .enterPassword("Admin");
//    }
//
//    @Then("The user is on the NHS home page")
//    public void theUserIsOnTheNHSHomePage() {
//        getPage(LoginPage.class).clickSignInButton();
//    }
}
