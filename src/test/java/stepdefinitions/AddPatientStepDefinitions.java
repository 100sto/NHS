package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.AddPatientPage;
import pages.HomePage;
import utils.DriverHelper;

import java.util.List;

public class AddPatientStepDefinitions {
    WebDriver driver = DriverHelper.getDriver();
    HomePage homePage = new HomePage(driver);
    AddPatientPage addPatientPage = new AddPatientPage(driver);
    List<String> existingHospitalNumbers;

    @When("The user clicks on 'Add patient' side bar")
    public void theUserClicksOnSection() {
        existingHospitalNumbers = homePage.getPatientHospitalNumberList();
        homePage.clickAddPatientSideBar();
    }

    @When("The user adds the patient details and clicks 'Add patient' button")
    public void theUserAddsThePatientDetailsAndClicksButton() {
        addPatientPage.addPatientFirstName()
                .addPatientLastName()
                .addPatientHospitalNumber(existingHospitalNumbers)
                .addPatientDOB()
                .addPatientGender()
                .addPatientDisease()
                .clickAddPatientButton();
    }

    @Then("The user can see the patient in 'Patients waiting' table")
    public void theUserCanSeeThePatientInTable() {
        homePage.verifyTheNewPatientIsAddedInTheWaitList();
    }
}
