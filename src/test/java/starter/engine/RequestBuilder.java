package starter.engine;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;

public class RequestBuilder {

    public static Response execute(JSONObject testCase) {
        JSONObject request = testCase.getJSONObject("request");

        String method = request.getString("method");
        String url = buildUrlWithPathParams(request);
        JSONObject headers = request.getJSONObject("headers");
        JSONObject body = request.has("body") ? request.getJSONObject("body") : new JSONObject();

        // 👉 Log del request
        System.out.println("📤 Sending " + method + " to " + url);
        System.out.println("🧾 Headers: " + headers.toString(2));
        System.out.println("📝 Body:\n" + body.toString(2));

        return SerenityRest.given()
                .headers(headers.toMap())
                .body(body.toString())
                .when()
                .request(method, url);
    }


    private static String buildUrlWithPathParams(JSONObject request) {
        String url = request.getString("url");
        if (request.has("pathParams")) {
            JSONObject pathParams = request.getJSONObject("pathParams");
            for (String key : pathParams.keySet()) {
                String placeholder = "{" + key + "}";
                String value = VariableMemory.resolve(pathParams.getString(key));
                url = url.replace(placeholder, value);
            }
        }
        return url;
    }
}

