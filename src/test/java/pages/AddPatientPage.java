package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;
import utils.DriverHelper;

import java.util.List;
import java.util.Random;

public class AddPatientPage extends BrowserUtils {
    public AddPatientPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@name='hospitalNumber']")
    private WebElement hospitalNumberField;

    @FindBy(xpath = "//input[@name='dateOfBirth']")
    private WebElement dateOfBirthField;

    @FindBy(xpath = "//input[@value='Add patient']")
    private WebElement addPatientButton;

    @FindBy(xpath = "//table[@id='add-new-patient']//td[@class='sorting_1']")
    private List<WebElement> existingDiseases;

    public AddPatientPage addPatientFirstName() {
        String patientFirstName = generateRandomFirstName();
        LOGGER.info("Entering '{}' in firstNameField", patientFirstName);
        setRunTimeProperty("patientFirstName", patientFirstName);
        firstNameField.sendKeys(patientFirstName);
        return this;
    }

    public AddPatientPage addPatientLastName() {
        String patientLastName = generateRandomLastName();
        LOGGER.info("Entering '{}' in lastNameField", patientLastName);
        setRunTimeProperty("patientLastName", patientLastName);
        lastNameField.sendKeys(patientLastName);
        return this;
    }

    public AddPatientPage addPatientHospitalNumber(List<String> existingHospitalNumbers) {
        Random random = new Random();
        int randomNumber;
        do {
            randomNumber = random.nextInt(1001);
        } while (existingHospitalNumbers.contains(String.valueOf(randomNumber)));
        LOGGER.info("Entering '{}' in hospitalNumberField", String.valueOf(randomNumber));
        hospitalNumberField.sendKeys(String.valueOf(randomNumber));
        return this;
    }

    public AddPatientPage addPatientDOB() {
        LOGGER.info("Entering '{}' in dateOfBirthField", generateRandomDate());
        dateOfBirthField.sendKeys(generateRandomDate());
        return this;
    }

    public AddPatientPage addPatientGender() {
        String patientGender = generateGender(getRunTimeProperty("patientFirstName"));
        LOGGER.info("Clicking '{}' gender", patientGender);
        WebElement genderButton = DriverHelper.getDriver()
                .findElement(By.xpath("//label[text()[contains(.,'" + patientGender + "')]]"));
        genderButton.click();
        return this;
    }

    public AddPatientPage addPatientDisease() {
        List<String> diseases = getTextListFromWebElements(existingDiseases);
        Random random = new Random();
        int index = random.nextInt(diseases.size());
        LOGGER.info("Clicking '{}' disease selected", diseases.get(index));
        WebElement diseaseCheckBox = DriverHelper.getDriver().findElement(By.xpath("//input[@value='" + diseases.get(index) + "']"));
        diseaseCheckBox.click();
        return this;
    }

    public AddPatientPage clickAddPatientButton() {
        LOGGER.info("Clicking addPatientButton");
        addPatientButton.click();
        return this;
    }
}
