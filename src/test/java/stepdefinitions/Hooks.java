package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigReared;
import utils.DriverHelper;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Hooks {
    WebDriver driver = DriverHelper.getDriver();
    static SoftAssertions softAssertions;

    @Before
    public void beforeScenario() {
        softAssertions = new SoftAssertions();
        driver.navigate().to(ConfigReared.readProperty("nhsURL"));
    }

    @After
    public void afterScenario(Scenario scenario) {
        softAssertions.assertAll();
        Date currentDate = new Date();
        String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
        if (scenario.isFailed()) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshotFile, new File(new File("src/test/java/screenshot/" + screenshotFileName) + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        DriverHelper.tearDown();
    }
}
