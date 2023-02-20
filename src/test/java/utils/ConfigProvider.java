package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ConfigProvider {
    private static final Logger LOGGER = LogManager.getLogger(ConfigProvider.class);
    private static Properties properties;
    private static Map<String, Properties> configMap = new HashMap<>();
    private static final String PROPERTIES_EXTENSION = ".properties";
    private static final String PROPERTIES_FOLDER = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "test" + File.separator + "resources" + File.separator + "configurations" + File.separator;

    private ConfigProvider() {

    }

    /**
     * static method to get the instance of the ConfigProvider
     *
     * @param propertyFileName the file to read properties from
     * @return Properties the ConfigProvider properties
     */
    private static Properties getInstance(String propertyFileName) {
        Properties props = null;
        if (configMap.size() == 0) {
            props = loadProperties(propertyFileName);
            configMap.put(propertyFileName, props);
            return props;
        }
        for (Map.Entry<String, Properties> entry : configMap.entrySet()) {
            if (entry.getKey().equals(propertyFileName)) {
                return entry.getValue();
            }
        }
        props = loadProperties(propertyFileName);
        configMap.put(propertyFileName, props);
        return props;
    }

    /**
     * static method to get the instance of the ConfigProvider
     *
     * @return Properties the ConfigProvider properties
     */
    public static Properties getInstance() {
        if (properties == null) {
            properties = loadProperties();
        }
        return properties;
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        File propertiesFile;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            propertiesFile = new File(PROPERTIES_FOLDER);
        } catch (NullPointerException e1) {
            LOGGER.error("No properties file found inside " + PROPERTIES_FOLDER + "folder. " +
                    "Please add all properties files under mentioned folder (create the folder if it doesn't exist)");
            throw new NullPointerException();
        }
        try {
            File[] listOfFiles = propertiesFile.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().endsWith(PROPERTIES_EXTENSION)) {
                    props.load(new FileInputStream(propertiesFile + File.separator + file.getName()));
                }
            }
        } catch (IOException e2) {
            LOGGER.error("Not able to load the property, please check the configuration file(s)");
        }
        return props;
    }

    private static Properties loadProperties(String propertyFile) {
        Properties props = new Properties();
        File propertiesFile = null;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream(PROPERTIES_FOLDER + File.separator
                + propertyFile + PROPERTIES_EXTENSION);

        try {
            props.load(is);
        } catch (NullPointerException e1) {
            LOGGER.error("'" + propertyFile + PROPERTIES_EXTENSION + "' file not found. Verify mentioned file to be " +
                    "present under 'resources'");
            throw new NullPointerException();
        } catch (IOException e2) {
            LOGGER.error("Not able to load the property, please check the configuration file(s)");
        }
        return props;
    }

    public static String getAsString(String key) {
        return getInstance().getProperty(key);
    }

    public static int getAsInt(String key) {
        return Integer.parseInt(getInstance().getProperty(key));
    }

    public static String getAsString(String fileName, String key) {
        return getInstance(fileName).getProperty(key);
    }

    public static int getAsInt(String fileName, String key) {
        return Integer.parseInt(getInstance(fileName).getProperty(key));
    }

    public static String getAsString(String environment, String propertyFile, String key) {
        Properties props = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream is = loader.getResourceAsStream(PROPERTIES_FOLDER + File.separator + environment
                + File.separator + propertyFile + PROPERTIES_EXTENSION);
        String value = null;
        try {
            props.load(is);
            value = props.getProperty(key);
            assert is != null;
            is.close();
        } catch (IOException | NullPointerException e) {
            LOGGER.error("An exception was thrown: {}", e.getMessage());
        }
        return value;
    }
}
