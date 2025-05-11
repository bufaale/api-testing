package starter.engine;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import starter.utils.DeepJsonComparator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ResponseValidator {

    public static void validateStatusCode(Response response, JSONObject testCase) {
        int expectedStatus = testCase.getJSONObject("response").getInt("statusCode");
        response.then().statusCode(expectedStatus);
    }

    public static void validateBodyAgainstExpectedJson(Response response, JSONObject testCase) {
        JSONObject expected = testCase.getJSONObject("response").getJSONObject("body");
        JSONObject constraints = testCase.getJSONObject("response").optJSONObject("constraints");

        boolean ignoreArrayOrder = constraints != null && constraints.optBoolean("ignoreArrayOrder", false);
        Set<String> ignorePaths = new HashSet<>();
        if (constraints != null && constraints.has("ignorePaths")) {
            JSONArray arr = constraints.getJSONArray("ignorePaths");
            for (int i = 0; i < arr.length(); i++) {
                ignorePaths.add(arr.getString(i));
            }
        }

        Object actual = response.as(Map.class);
        DeepJsonComparator.assertJsonEquals(expected, actual, ignorePaths, ignoreArrayOrder);
    }


}
