package starter.model;

public class TestRequestDefinition {

    private String definitionPath;
    private TestSection section; // ✅ Enum en vez de String
    private String saveRequestAs;
    private String saveResponseAs;

    public TestRequestDefinition(String definitionPath, String section, String saveRequestAs, String saveResponseAs) {
        this.definitionPath = definitionPath;
        this.section = TestSection.fromString(section); // 👈 conversión segura
        this.saveRequestAs = saveRequestAs;
        this.saveResponseAs = saveResponseAs;
    }

    public String getDefinitionPath() {
        return definitionPath;
    }

    public TestSection getSection() {
        return section;
    }

    public String getSaveRequestAs() {
        return saveRequestAs;
    }

    public String getSaveResponseAs() {
        return saveResponseAs;
    }
}

