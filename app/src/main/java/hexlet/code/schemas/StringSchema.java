package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        addCheck("required", str -> str != null && !str.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", str -> str.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", str -> str.contains(substring));
        return this;
    }

    /**
     * Validates a {@code String} value with special consideration for empty strings.
     * <p>
     * This override adds custom behavior for {@code String} instances:
     * <ol>
     *   <li>If the provided value is an instance of {@code String} and the schema does not have a "required"
     *       check, an empty string is considered valid.</li>
     *   <li>For all other cases, the method delegates to the base implementation of {@code isValid(Object)}.</li>
     * </ol>
     * This behavior ensures that, unless explicitly marked as required (via {@link #required()}),
     * empty strings do not trigger a validation error.
     * </p>
     *
     * @param value the {@code String} value to validate.
     * @return {@code true} if the string is valid based on the above rules
     * or passes the base validations; {@code false} otherwise.
     */
    @Override
    public boolean isValid(Object value) {
        if (value instanceof String) {
            String str = (String) value;
            if (!checks.containsKey("required") && str.isEmpty()) {
                return true;
            }
        }
        return super.isValid(value);
    }

    @Override
    protected Class<String> getType() {
        return String.class;
    }
}
