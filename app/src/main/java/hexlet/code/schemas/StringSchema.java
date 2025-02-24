package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    @Override
    public final StringSchema required() {
        addCheck("required", str -> str != null && !str.isEmpty());
        return this;
    }

    public final StringSchema minLength(int length) {
        addCheck("minLength", str -> str.length() >= length);
        return this;
    }

    public final StringSchema contains(String substring) {
        addCheck("contains", str -> str.contains(substring));
        return this;
    }

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
    protected final Class<String> getType() {
        return String.class;
    }
}
