package starter.model;

public enum TestSection {
    FIRST_PRECONDITION,
    SECOND_PRECONDITION,
    THIRD_PRECONDITION,
    TEST;

    public static TestSection fromString(String value) {
        try {
            return TestSection.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("❌ Invalid section: " + value);
        }
    }
}
