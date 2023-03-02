package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.SystemSettingsPage;
import utils.DriverHelper;

public class SystemSettingsStepDefinitions {
    WebDriver driver = DriverHelper.getDriver();
    SystemSettingsPage systemSettingsPage = new SystemSettingsPage(driver);

    @And("The user adds new disease in the system and clicks 'Add disease' button")
    public void theUserAddsNewDiseaseInTheSystemAndClicksAddDiseaseButton() {
        systemSettingsPage.enterNewDiseaseInTheSystem()
                .enterDiseaseScore()
                .clickAddDiseaseButton();
    }

    @Then("The user can see the disease in 'Delete diseases from the system' table")
    public void theUserCanSeeTheDiseaseInDeleteDiseasesFromTheSystemTable() {
        systemSettingsPage.verifyTheNewDiseaseWasAdded();
    }
}
