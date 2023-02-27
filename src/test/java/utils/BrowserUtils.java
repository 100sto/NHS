package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class BrowserUtils {
    public static final Logger LOGGER = LogManager.getLogger();

    public static void selectBy(WebElement element, String value, String methodName) {
        Select select = new Select(element);
        switch (methodName) {
            case "text" -> select.selectByVisibleText(value);
            case "index" -> select.selectByIndex(Integer.parseInt(value));
            case "value" -> select.selectByValue(value);
            default -> System.out.println("Method name is not available, Use text, value or index for method name");
        }
    }

    public static String getTextMethod(WebElement element) {
        return element.getText().trim();
    }

    public static String getTitleWithJavaScript(WebDriver driver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript("return document.title").toString();
    }

    public static void clickWithJavaScript(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()", element);

    }

    public static void scrollWithJavaScript(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void scrollWithXAndY(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Point location = element.getLocation();
        int xCord = location.getX();
        int yCord = location.getY();
        js.executeScript("window.scrollTo(" + xCord + "," + yCord + ")");
    }

    public static void SwitchOnlyFor2Tabs(WebDriver driver, String mainPageId ){
        Set<String> allPagesId = driver.getWindowHandles();
        for(String id : allPagesId){
            if(!id.equals(mainPageId)){
                driver.switchTo().window(id);
            }
        }
    }

    public static void switchByTitle(WebDriver driver, String title) {
        Set<String> allPagesId = driver.getWindowHandles();
        for (String id : allPagesId) {
            driver.switchTo().window(id);
            if (driver.getTitle().contains(title)) {
                break;
            }
        }
    }

    public static void getScreenShot(WebDriver driver, String packageName) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String location = System.getProperty("user.dir") + "/src/java/screenshot" + packageName;
        try {
            FileUtils.copyFile(file, new File(location + System.currentTimeMillis()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void waitForSeconds(int seconds) {
        LOGGER.info("Driver will wait for {} seconds", seconds);
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            LOGGER.warn("Exception in waitForSeconds() method");
            Assert.fail(e.getMessage());
        }
    }

    public static void waitForPageToLoad(WebDriver driver, int timeoutInSeconds) {
        Duration durationSeconds = Duration.ofSeconds(timeoutInSeconds);
        WebDriverWait wait = new WebDriverWait(driver, durationSeconds);
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
    }

    public static String generateRandomNumberAsString() {
        Random random = new Random();
        return String.valueOf(random.nextInt(1001));
    }

    public static String generateRandomFirstName() {
        List<String> firstNames = Arrays.asList("Emma", "Liam", "Olivia", "Noah", "Ava", "Ethan", "Sophia", "Lucas", "Isabella", "Mia", "Mason", "Charlotte", "Amelia", "Elijah", "Harper", "Logan", "Aiden", "Evelyn", "Ella", "Carter");
        Random random = new Random();
        int index = random.nextInt(firstNames.size());
        return firstNames.get(index);
    }

    public static String generateRandomLastName() {
        List<String> lastNames = Arrays.asList("Smith", "Johnson", "Brown", "Garcia", "Miller", "Davis", "Gonzalez", "Wilson", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Moore", "Young", "Allen", "King", "Wright");
        Random random = new Random();
        int index = random.nextInt(lastNames.size());
        return lastNames.get(index);
    }

    public static String generateRandomDate() {
        Random random = new Random();
        int year = random.nextInt(74) + 1950; // generates a random year between 1950 and 2023
        int month = random.nextInt(12) + 1; // generates a random month between 1 and 12
        int day = random.nextInt(LocalDate.of(year, month, 1).lengthOfMonth()) + 1; // generates a random day between 1 and the number of days in the month
        return String.format("%02d/%02d/%04d", day, month, year);
    }

    public static String generateGender(String firstName) {
        List<String> maleNames = Arrays.asList("Liam", "Noah", "Ethan", "Lucas", "Mason", "Elijah", "Logan", "Aiden", "Carter");
        List<String> femaleNames = Arrays.asList("Emma", "Olivia", "Ava", "Sophia", "Isabella", "Mia", "Charlotte", "Amelia", "Harper", "Evelyn", "Ella");
        if (maleNames.contains(firstName)) {
            return "Male";
        } else if (femaleNames.contains(firstName)) {
            return "Female";
        } else {
            return "Unknown";
        }
    }

    public static String getRunTimeProperty(String key) {
        return System.getProperty(key);
    }

    public static void setRunTimeProperty(String key, String value) {
        System.setProperty(key, value);
    }

//    private static final Properties properties = new Properties();
//
//    public static void serRuntimeProperty(String key, String value) {
//        properties.setProperty(key, value);
//    }
//
//    public static String getProperty(String key) {
//        return properties.getProperty(key);
//    }






    public void doSomething() {
        LOGGER.debug("Debug message");
        LOGGER.info("Info message");
        LOGGER.warn("Warn message");
        LOGGER.error("Error message");
        LOGGER.fatal("Fatal message");

        String name = "John";
        int age = 25;
        LOGGER.info("User {} is {} years old", name, age);

        try {
            // code that throws an exception
        } catch (Exception e) {
            LOGGER.error("An error occurred", e);
        }
    }
}
