package utils;

import lombok.Getter;
import lombok.SneakyThrows;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

// Singleton pattern
public class PropertyReader {
    @Getter
    private static PropertyReader instance = new PropertyReader();
    private final Properties properties;

    public static String UiUrl = PropertyReader.getInstance().getUiUrl();

    public static String ApiUrl = PropertyReader.getInstance().getApiUrl();
    public static String ApiKeyHeader = PropertyReader.getInstance().getApiKeyHeader();
    public static String ApiKey = PropertyReader.getInstance().getApiKey();
    public static String ApiUsername = PropertyReader.getInstance().getApiUsername();
    public static String ApiEmail = PropertyReader.getInstance().getApiEmail();
    public static String ApiPassword = PropertyReader.getInstance().getApiPassword();

    public static String ApiId = PropertyReader.getInstance().getApiId();
    public static String ApiJob = PropertyReader.getInstance().getApiJob();

    @SneakyThrows
    public PropertyReader() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/test/resources/test.properties"))) {
            properties.load(reader);
        }
    }
    public String getHeadless() { return properties.getProperty("headless"); }
    public String getBrowser() {return properties.getProperty("browser");}

    public String getUiUrl(){ return properties.getProperty("UI_url");}
    public String getUIPassword(){
        return properties.getProperty("UI_password");
    }
    public String getUIUsername(){
        return properties.getProperty("UI_username");
    }

    public String getApiUrl(){
        return properties.getProperty("API_url");
    }
    public String getApiKeyHeader(){
        return properties.getProperty("API_KeyHeader");
    }
    public String getApiKey(){ return properties.getProperty("API_Key"); }

    public String getApiUsername() {return properties.getProperty("ApiUsername");}
    public String getApiEmail() {return properties.getProperty("ApiEmail");}
    public String getApiPassword() {return properties.getProperty("ApiPassword");}

    public String getApiId() {return properties.getProperty("ApiId");}
    public String getApiJob() {return properties.getProperty("ApiJob");}
}
