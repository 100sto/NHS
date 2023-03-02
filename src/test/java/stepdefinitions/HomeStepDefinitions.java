package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import utils.DriverHelper;

public class HomeStepDefinitions {
    WebDriver driver = DriverHelper.getDriver();
    HomePage homePage = new HomePage(driver);

    @Then("The user can see the 3 tabs displayed on the home page")
    public void theUserCanSeeTheTabsDisplayedOnTheHomePage() {
        homePage.verifyTheHomePageTabs();
    }

    @Then("The user can see the Patients with rooms table displayed on the home page")
    public void theUserCanSeeThePatientsWithRoomsTableDisplayedOnTheHomePage() {
        homePage.verifyPatientsWithRoomsTableHeaders();
    }

    @And("The user clicks on 'System settings' side bar")
    public void theUserClicksOnSystemSettingsSideBar() {
        homePage.clickSystemSettingsSideBar();
    }
}
