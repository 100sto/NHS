package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;
import utils.DriverHelper;

public class HomePage extends BrowserUtils {
    private static final Logger LOGGER = LogManager.getLogger();

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@id='patients-with-rooms-live']/..//div[contains(text(),'Patients with rooms')]")
    private WebElement textPatientsWithRooms;

    @FindBy(xpath = "//div[@id='patients-waiting-live']/..//div[contains(text(),'Patients waiting')]")
    private WebElement textPatientsWaiting;

    @FindBy(xpath = "//div[@id='free-rooms-live']/..//div[contains(text(),'Free rooms')]")
    private WebElement textFreeRooms;

    public HomePage verifyHomePageTitle(SoftAssertions softAssertions) {
        LOGGER.info("Verify the home page title");
        softAssertions.assertThat(DriverHelper.getDriver().getTitle()).isEqualTo("NHS patients");
        return this;
    }

    public HomePage verifyTheHomePageTabs(SoftAssertions softAssertions) {
        softAssertions.assertThat(getTextMethod(textPatientsWithRooms)).isEqualTo("Patients with rooms");
        softAssertions.assertThat(getTextMethod(textPatientsWaiting)).isEqualTo("Patients waiting");
        softAssertions.assertThat(getTextMethod(textFreeRooms)).isEqualTo("Free rooms");
        return this;
    }
}
