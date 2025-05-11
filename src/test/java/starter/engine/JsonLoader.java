package starter.engine;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class JsonLoader {

    public static JSONObject load(String fileName) {
        try (InputStream is = JsonTestExecutor.class.getClassLoader().getResourceAsStream(fileName)) {
            if (is == null) {
                throw new FileNotFoundException("File not found in resources: " + fileName);
            }
            String content = new Scanner(is, StandardCharsets.UTF_8).useDelimiter("\\A").next();
            return new JSONObject(content);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON from: " + fileName, e);
        }
    }
}
