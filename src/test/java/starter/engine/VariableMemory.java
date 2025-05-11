package starter.engine;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableMemory {

    private static final Map<String, String> memory = new HashMap<>();

    public static void rememberValues(Response response, JSONObject testCase) {
        if (!testCase.has("remember")) return;

        JSONArray memories = testCase.getJSONObject("remember").getJSONArray("fromResponse");
        for (int i = 0; i < memories.length(); i++) {
            JSONObject item = memories.getJSONObject(i);
            String jsonPath = item.getString("jsonPath").replace("$.","");
            String saveAs = item.getString("saveAs");
            String value = response.jsonPath().getString(jsonPath);
            memory.put(saveAs, value);
            System.out.println("🔐 Saved variable '" + saveAs + "' = " + value);

        }

    }

    public static String resolve(String value) {
        Pattern pattern = Pattern.compile("\\{\\{(.+?)}}");
        Matcher matcher = pattern.matcher(value);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = memory.getOrDefault(key, "");
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
