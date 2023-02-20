package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private static final Logger LOGGER = LogManager.getLogger();

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

//    @FindBy ()
//    private WebElement
}
