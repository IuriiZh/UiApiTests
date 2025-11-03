package api.util;

import java.io.IOException;

public class ReadProperties {

    public static String readTestDataProperties(String data) {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("dev.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return System.getProperty(data);
    }
}
