package starter.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class DeepJsonComparator {

    public static void assertJsonEquals(Object expected, Object actual, Set<String> ignorePaths, boolean ignoreArrayOrder) {
        assertJsonEquals(expected, actual, "", ignorePaths, ignoreArrayOrder);
    }

    private static void assertJsonEquals(Object expected, Object actual, String path, Set<String> ignorePaths, boolean ignoreArrayOrder) {
        if (ignorePaths.contains(path)) return;

        if (expected instanceof JSONObject && actual instanceof Map) {
            JSONObject expectedJson = (JSONObject) expected;
            Map<?, ?> actualMap = (Map<?, ?>) actual;

            for (String key : expectedJson.keySet()) {
                String newPath = path.isEmpty() ? key : path + "." + key;
                Object expectedVal = expectedJson.get(key);
                Object actualVal = actualMap.get(key);
                assertJsonEquals(expectedVal, actualVal, newPath, ignorePaths, ignoreArrayOrder);
            }

        } else if (expected instanceof JSONArray && actual instanceof List<?>) {
            JSONArray expectedArray = (JSONArray) expected;
            List<?> actualList = (List<?>) actual;

            if (ignoreArrayOrder) {
                List<String> expectedStr = new ArrayList<>();
                for (int i = 0; i < expectedArray.length(); i++) {
                    expectedStr.add(expectedArray.get(i).toString());
                }
                List<String> actualStr = actualList.stream().map(Object::toString).collect(Collectors.toList());
                Collections.sort(expectedStr);
                Collections.sort(actualStr);
                assertThat("Array mismatch at path: " + path, actualStr, equalTo(expectedStr));
            } else {
                for (int i = 0; i < expectedArray.length(); i++) {
                    String newPath = path + "[" + i + "]";
                    assertJsonEquals(expectedArray.get(i), actualList.get(i), newPath, ignorePaths, ignoreArrayOrder);
                }
            }

        } else {
            assertThat("Value mismatch at path: " + path, String.valueOf(actual), equalTo(String.valueOf(expected)));
        }
    }
}
