package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SystemSettingsPage extends BrowserUtils {
    public SystemSettingsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@name='diseaseName']")
    private WebElement diseaseNameField;

    @FindBy(xpath = "//input[@name='diseaseScore']")
    private WebElement diseaseScoreField;

    @FindBy(xpath = "//input[@value='Add disease']")
    private WebElement addDiseaseButton;

    @FindBy(xpath = "//table[@id='diseases-table']//td[@class='sorting_1']")
    private List<WebElement> existingDiseases;

    public String generateRandomDiseaseName(List<String> existingDiseaseNames) {
        List<String> allDiseaseNames = Arrays.asList("Schizophrenia", "Headache", "Asbestosis", "Alzheimer", "Allergies", "Acne", "Cancer", "Diabetes", "Arthritis", "Asthma", "Eczema", "Flu", "Measles", "Malaria", "Hepatitis", "Cholera", "Tuberculosis", "Meningitis", "Dengue", "Zika", "Ebola", "HIV", "AIDS", "Chikungunya", "Mumps", "Rubella");
//        List<String> existingDiseaseNames = getTextListFromWebElements(existingDiseases);
        List<String> availableDiseaseNames = new ArrayList<String>(allDiseaseNames);
        availableDiseaseNames.removeAll(existingDiseaseNames);
        if (availableDiseaseNames.isEmpty()) {
            throw new RuntimeException("No available disease names found.");
        }
        Random random = new Random();
        int index = random.nextInt(availableDiseaseNames.size());
        return availableDiseaseNames.get(index);
    }

//    public String generateRandomDiseaseName() {
//        List<String> allDiseaseNames = Arrays.asList("Schizophrenia", "Headache", "Asbestosis", "Alzheimer", "Allergies", "Acne", "Cancer", "Diabetes", "Arthritis", "Asthma", "Eczema", "Flu", "Measles", "Malaria", "Hepatitis", "Cholera", "Tuberculosis", "Meningitis", "Dengue", "Zika", "Ebola", "HIV", "AIDS", "Chikungunya", "Mumps", "Rubella");
//        List<String> existingDiseaseNames = getTextListFromWebElements(existingDiseases);
//        allDiseaseNames.removeAll(existingDiseaseNames);
//        Random random = new Random();
//        int index = random.nextInt(allDiseaseNames.size());
//        return allDiseaseNames.get(index);
//    }

    public SystemSettingsPage enterNewDiseaseInTheSystem() {
        String disease = generateRandomDiseaseName(getTextListFromWebElements(existingDiseases));
        setRunTimeProperty("disease", disease);
        LOGGER.info("Entering new disease '{}' in diseaseNameField", disease);
        diseaseNameField.sendKeys(disease);
        return this;
    }

    public SystemSettingsPage enterDiseaseScore() {
        String score = generateRandomNumberAsString(51);
        LOGGER.info("Entering new disease '{}' in diseaseNameField", score);
        diseaseScoreField.sendKeys(score);
        return this;
    }

    public SystemSettingsPage verifyTheNewDiseaseWasAdded() {
        List<String> existingDiseaseNames = getTextListFromWebElements(existingDiseases);
        String disease = getRunTimeProperty("disease");
        LOGGER.info("Verify disease '{}' was added to the table", disease);
        Assert.assertTrue(existingDiseaseNames.contains(disease));
        return this;
    }

    public SystemSettingsPage clickAddDiseaseButton() {
        LOGGER.info("Clicking addDiseaseButton");
        addDiseaseButton.click();
        return this;
    }
}
