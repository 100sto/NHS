package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.Properties;

public class SystemVsConfigProperties {
    private static final Logger LOGGER = LogManager.getLogger(SystemVsConfigProperties.class);
    Properties runtimeProperties = null;

    public SystemVsConfigProperties() {
        loadConfigProperties();
        setDefaultRuntimeProperties();
    }

    public String getProperty(String propertyKey) {
        if (System.getProperty(propertyKey) == null) {
            loadConfigProperties();
            setRunTimeProperties(propertyKey);
        }
        return System.getProperty(propertyKey);
    }

    private void loadConfigProperties() {
        runtimeProperties = ConfigProvider.getInstance();
    }

    public enum SystemPropertiesVariables {
        environment, tagToRun, nhsURL, browser, nhsUsername, nhsPassword, forkCount, parallelSchema
    }

    private void setDefaultRuntimeProperties() {
        if (System.getProperty("environment") == null)
            System.setProperty("environment", ConfigProvider.getAsString(("environment")));

        if (System.getProperty("tagToRun") == null)
            System.setProperty("tagToRun", ConfigProvider.getAsString(("tagToRun")));

        if (System.getProperty("browser") == null)
            System.setProperty("browser", ConfigProvider.getAsString(("browser")));
    }

    private void setRunTimeProperties(final String propertyKey) {
        LOGGER.info("Accessing the {} environment", System.getProperty("environment"));
        switch (System.getProperty("environment")) {
            case "test" -> urlHelper("test", propertyKey);
            case "stage" -> urlHelper("stage", propertyKey);
            case "prod" -> urlHelper("prod", propertyKey);
            default -> {
                LOGGER.warn(System.getProperty("environment") + " does not exist");
                Assert.fail(System.getProperty("environment") + " does not exist");
            }
        }
    }

    private void urlHelper(String environment, String appMnemonic) {
        if (!isEmptyEnvironment(runtimeProperties.getProperty(appMnemonic + "_" + environment))) {
            System.setProperty(appMnemonic, runtimeProperties.getProperty(appMnemonic + "_" + environment));
        } else {
            LOGGER.warn(appMnemonic + "_" + environment + " return null, please add the url for this environment " +
                    "in config.properties");
            System.setProperty(appMnemonic, "return null, please add the url for this environment in config.properties");
        }
    }

    private boolean isEmptyEnvironment(final String str) {
        return ((str == null) || (str.isEmpty()));
    }
}
