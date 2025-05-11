package starter.stepdefinitions.trasnformers;

import io.cucumber.java.DataTableType;
import starter.model.TestRequestDefinition;

import java.util.Map;

public class TestRequestTransformer {

    @DataTableType
    public TestRequestDefinition transform(Map<String, String> entry) {
        return new TestRequestDefinition(
                entry.getOrDefault("testDefinitionPath", ""),
                entry.getOrDefault("section", ""), // será validado y convertido en el constructor
                entry.getOrDefault("saveRequestAs", "EMPTY"),
                entry.getOrDefault("saveResponseAs", "EMPTY")
        );
    }
}
