package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    public BaseSchema<T> required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

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
