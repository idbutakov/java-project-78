package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    public BaseSchema<T> required() {
        isRequired = true;
        return this;
    }

    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        if (!getType().isInstance(value)) {
            return false;
        }

        T castedValue = getType().cast(value);
        return validate(castedValue);
    }

    protected abstract Class<T> getType();

    protected abstract boolean validate(T value);
}
