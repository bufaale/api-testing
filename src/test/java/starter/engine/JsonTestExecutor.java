package starter.engine;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import starter.model.TestRequestDefinition;
import starter.model.TestSection;

import java.util.Optional;
import java.util.stream.IntStream;


public class JsonTestExecutor {

    public static void runTestFromJson(TestRequestDefinition def) {
        try {
            JSONObject root = JsonLoader.load(def.getDefinitionPath());
            JSONObject testCase;

            if (def.getSection() == TestSection.TEST) {
                if (!root.has("test")) {
                    throw new IllegalArgumentException("❌ No test section defined in JSON.");
                }
                testCase = root.getJSONObject("test");

            } else {
                if (!root.has("preconditions")) {
                    throw new IllegalArgumentException("❌ No preconditions defined in JSON.");
                }

                JSONArray preconditions = root.getJSONArray("preconditions");
                int targetOrder = def.getSection().ordinal();

                Optional<JSONObject> match = IntStream.range(0, preconditions.length())
                        .mapToObj(preconditions::getJSONObject)
                        .filter(obj -> obj.has("order") && obj.getInt("order") == targetOrder)
                        .findFirst();

                testCase = match.orElseThrow(() ->
                        new IllegalArgumentException("❌ No precondition found for order " + targetOrder + " (" + def.getSection().name() + ")"));
            }

            Response response = RequestBuilder.execute(testCase);
            System.out.println("📥 Response status: " + response.getStatusCode());
            System.out.println("📦 Response body:\n" + response.getBody().prettyPrint());
            ResponseValidator.validateStatusCode(response, testCase);
            ResponseValidator.validateBodyAgainstExpectedJson(response, testCase);
            VariableMemory.rememberValues(response, testCase);

        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to execute test case for section " + def.getSection().name() + ": " + e.getMessage(), e);
        }
    }

}



