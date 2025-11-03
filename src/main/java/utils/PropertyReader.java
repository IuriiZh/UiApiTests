package utils;

import lombok.Getter;
import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

// Singleton pattern
public class PropertyReader {
    @Getter
    private final static PropertyReader instance = new PropertyReader();
    private final Properties properties;

    public static String UiUrl = PropertyReader.getInstance().getUiUrl();


    @SneakyThrows
    public PropertyReader() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/test/resources/test.properties"))) {
            properties.load(reader);
        }
    }

    public String getHeadless() {
        return properties.getProperty("headless");
    }
    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getUiUrl() {
        return properties.getProperty("UI_url");
    }

}
