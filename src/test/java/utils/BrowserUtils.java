package utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class BrowserUtils {

    //For select class
    //We create browserUtil to be able to call reusable methods from here to reduce amount of the codes
    public static void selectBy(WebElement element, String value, String methodName) {
        Select select = new Select(element);

        switch (methodName) {
            case "text":
                select.selectByVisibleText(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            case "value":
                select.selectByValue(value);
                break;
            default:
                System.out.println("Method name is not available, Use text, value or index for method name");
        }
    }

    //get text from an WebElement trimmed
    public static String getTextMethod(WebElement element) {

        return element.getText().trim();

    }

    //getTitle with help of JavaScript
    public static String getTitleWithJavaScript(WebDriver driver) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        return javascriptExecutor.executeScript("return document.title").toString();
    }

    //click with JavaScript
    public static void clickWithJavaScript(WebDriver driver, WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click()", element);

    }

    //Scroll With Javascript
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

//    public static void SwitchOnlyFor2Tabs(WebDriver driver, String mainPageId ){
//
//        Set<String> allPagesId = driver.getWindowHandles();
//
//        for(String id : allPagesId){
//            if(!id.equals(mainPageId)){
//                driver.switchTo().window(id);
//            }
//        }
//
//    }


    //in real work this will help you a lot once you test different tabs or windows
    public static void switchByTitle(WebDriver driver, String title) {
        Set<String> allPagesId = driver.getWindowHandles();
        for (String id : allPagesId) { // internet-->new Window
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


    public static void print() {
        System.out.println();
    }

    private static final Logger logger = LogManager.getLogger(BrowserUtils.class);


    public void doSomething() {
        logger.debug("Debug message");
        logger.info("Info message");
        logger.warn("Warn message");
        logger.error("Error message");
        logger.fatal("Fatal message");

        String name = "John";
        int age = 25;
        logger.info("User {} is {} years old", name, age);

        try {
            // code that throws an exception
        } catch (Exception e) {
            logger.error("An error occurred", e);
        }
    }

}
