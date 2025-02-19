package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String containsSubstring = null;

    @Override
    public final StringSchema required() {
        super.required();
        return this;
    }

    public final StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public final StringSchema contains(String substring) {
        this.containsSubstring = substring;
        return this;
    }

    @Override
    protected final Class<String> getType() {
        return String.class;
    }

    @Override
    protected final boolean validate(String str) {
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
