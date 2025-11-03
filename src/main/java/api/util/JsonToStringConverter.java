package api.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonToStringConverter {

    public static String convertJsonToString(String file) {
        try {
            return Files.readAllLines(Paths.get(file))
                    .stream()
                    .map(String::trim)
                    .map(x -> x.replace(" ", ""))
                    .reduce(String::concat)
                    .orElseThrow(FileNotFoundException::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
