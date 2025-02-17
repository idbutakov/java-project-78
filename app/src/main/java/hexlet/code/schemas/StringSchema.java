package hexlet.code.schemas;

public class StringSchema {
    private boolean isRequired = false;
    private Integer minLength = null;
    private String containsSubstring = null;

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        if (!(value instanceof String str)) {
            return false;
        }

        if (!isRequired && str.isEmpty()) {
            return true;
        }

        if (isRequired && str.isEmpty()) {
            return false;
        }

        if (minLength != null && str.length() < minLength) {
            return false;
        }

        return containsSubstring == null || str.contains(containsSubstring);
    }
}
