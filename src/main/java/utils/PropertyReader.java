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


    @SneakyThrows
    public PropertyReader() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/test/resources/test.properties"))) {
            properties.load(reader);
        }
    }
}
