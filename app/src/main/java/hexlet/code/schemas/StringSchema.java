package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

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

    @Override
    public final boolean isValid(Object value) {
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
