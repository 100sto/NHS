package stepdefinitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverHelper;

public class HomeStepDefinitions {
    WebDriver driver = DriverHelper.getDriver();
    HomePage homePage = new HomePage(driver);

    @Then("The user can see the 3 tabs displayed on the home page")
    public void theUserCanSeeTheTabsDisplayedOnTheHomePage() {
        homePage.verifyTheHomePageTabs(Hooks.softAssertions);
    }
}
