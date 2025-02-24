package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    /**
     * Marks this schema as required by adding a check that disallows {@code null} values.
     * <p>
     * When this method is called, a non-null predicate is added to the schema (using
     * {@link Objects#nonNull}). This ensures that any value validated by {@link #isValid(Object)}
     * must be non-null to pass validation. In other words, if a {@code null} value is provided,
     * {@code isValid} will return {@code false} when this required check is present.
     * </p>
     * <p>
     * Note that for schemas handling {@code String} values, there is an override of {@code isValid}
     * that treats an empty string as valid if the required check is <em>not</em> set. Therefore,
     * if you want to enforce that strings are not empty as well as non-null, additional checks should
     * be added.
     * </p>
     *
     * @return this schema instance to allow for method chaining.
     */
    public BaseSchema<T> required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    /**
     * Validates the provided value against all checks registered in this schema.
     * <p>
     * The validation proceeds as follows:
     * <ol>
     *   <li>If the value is {@code null}, the method returns {@code false} if a "required" check
     *       is present; otherwise, it returns {@code true}.</li>
     *   <li>If the value is not {@code null}, it first verifies that the value is an instance of the
     *       expected type (obtained via {@link #getType()}). If it is not, the method returns {@code false}.</li>
     *   <li>The value is then cast to the expected type and passed through all registered predicate
     *       checks. If any check fails (returns {@code false}), the validation stops and the method
     *       returns {@code false}.</li>
     *   <li>If all checks pass, the method returns {@code true}.</li>
     * </ol>
     * </p>
     *
     * @param value the value to validate; may be {@code null} unless a "required" check has been added.
     * @return {@code true} if the value passes all validations; {@code false} otherwise.
     */
    public boolean isValid(Object value) {
        if (value == null) {
            return !checks.containsKey("required");
        }

        if (!getType().isInstance(value)) {
            return false;
        }
        T castedValue = getType().cast(value);

        for (Predicate<T> check : checks.values()) {
            if (!check.test(castedValue)) {
                return false;
            }
        }
        return true;
    }

    protected final void addCheck(String checkName, Predicate<T> check) {
        checks.put(checkName, check);
    }

    protected abstract Class<T> getType();
}
