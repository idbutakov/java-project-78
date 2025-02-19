package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;

    /**
     * Помечает схему как обязательную.
     * <p>
     * Если метод переопределяется в подклассах, рекомендуется вызывать {@code super.required()}
     * для обеспечения корректного поведения (например, установки флага {@code isRequired}).
     *
     * @return этот объект схемы с установленным флагом обязательности
     */
    public BaseSchema<T> required() {
        isRequired = true;
        return this;
    }

    public final boolean isValid(Object value) {
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
