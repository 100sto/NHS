package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;
import utils.DriverHelper;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BrowserUtils {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='patients-with-rooms-live']/..//div[contains(text(),'Patients with rooms')]")
    private WebElement textPatientsWithRooms;

    @FindBy(xpath = "//div[@id='patients-waiting-live']/..//div[contains(text(),'Patients waiting')]")
    private WebElement textPatientsWaiting;

    @FindBy(xpath = "//div[@id='free-rooms-live']/..//div[contains(text(),'Free rooms')]")
    private WebElement textFreeRooms;

    @FindBy(xpath = "//div[@id='patients-in-hospital_wrapper']//th[text()[contains(.,'no.')]]")
    private WebElement inHospitalNoColumn;

    @FindBy(xpath = "//div[@id='patients-in-hospital_wrapper']//th[text()[contains(.,'Patients with rooms')]]")
    private WebElement inHospitalPatientsWithRoomsColumn;

    @FindBy(xpath = "//div[@id='patients-in-hospital_wrapper']//th[text()[contains(.,'Room')]]")
    private WebElement inHospitalRoomColumn;

    @FindBy(xpath = "//div[@id='patients-in-hospital_wrapper']//th[text()[contains(.,'Score')]]")
    private WebElement inHospitalScoreColumn;

    @FindBy(xpath = "//a[text()[contains(.,'Add patient')]]")
    private WebElement addPatientSideBar;

    @FindBy(xpath = "//a[text()[contains(.,'System settings')]]")
    private WebElement systemSettingsSideBar;

    @FindBy(xpath = "//table[@id='patients-waiting']//td")
    private List<WebElement> patientsWaitingTableResults;

    public HomePage verifyHomePageTitle() {
        LOGGER.info("Verify the home page title");
        Assert.assertEquals("NHS patients", DriverHelper.getDriver().getTitle());
        return this;
    }

    public HomePage verifyTheHomePageTabs() {
        LOGGER.info("Verify home page tabs");
        Assert.assertEquals("Patients with rooms", getTextMethod(textPatientsWithRooms));
        Assert.assertEquals("Patients waiting", getTextMethod(textPatientsWaiting));
        Assert.assertEquals("Free rooms", getTextMethod(textFreeRooms));
        return this;
    }

    public HomePage verifyPatientsWithRoomsTableHeaders() {
        LOGGER.info("Verify Patients with rooms");
        Assert.assertEquals("no.", getTextMethod(inHospitalNoColumn));
        Assert.assertEquals("Patients with rooms", getTextMethod(inHospitalPatientsWithRoomsColumn));
        Assert.assertEquals("Room", getTextMethod(inHospitalRoomColumn));
        Assert.assertEquals("Score", getTextMethod(inHospitalScoreColumn));
        return this;
    }

    public HomePage clickAddPatientSideBar() {
        LOGGER.info("Click addPatientSideBar");
        addPatientSideBar.click();
        return this;
    }

    public HomePage clickSystemSettingsSideBar() {
        LOGGER.info("Click systemSettingsSideBar");
        systemSettingsSideBar.click();
        return this;
    }

    public List<String> getPatientHospitalNumberList() {
        waitForPageToLoad(DriverHelper.getDriver(), 5);
        List<String> hospitalNumbers = new ArrayList<>();
        for (int i = 0; i < patientsWaitingTableResults.size(); i += 3) {
            hospitalNumbers.add(getTextMethod(patientsWaitingTableResults.get(i)));
        }
        LOGGER.info("The existing hospital numbers are: {}", hospitalNumbers);
        return hospitalNumbers;
    }

    public List<String> getPatientNamesList() {
        List<String> patientNames = new ArrayList<>();
        for (int i = 1; i < patientsWaitingTableResults.size(); i += 3) {
            patientNames.add(getTextMethod(patientsWaitingTableResults.get(i)));
        }
        LOGGER.info("The patients names are: {}", patientNames);
        return patientNames;
    }

    public HomePage verifyTheNewPatientIsAddedInTheWaitList() {
        String patient = getRunTimeProperty("patientFirstName") + " " + getRunTimeProperty("patientLastName");
        LOGGER.info("Verify the waiting list contains the new added patient '{}'", patient);
        Assert.assertTrue(getPatientNamesList().contains(patient));
        return this;
    }
}
