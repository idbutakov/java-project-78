package hexlet.code.schemas;

public class NumberSchema {
    private boolean isRequired = false;
    private boolean isPositive = false;
    private boolean hasRange = false;
    private Integer start = 0;
    private Integer end = 0;

    public NumberSchema required() {
        isRequired = true;
        return this;
    }

    public NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public NumberSchema range(int a, int b) {
        this.start = a;
        this.end = b;
        hasRange = true;
        return this;
    }

    public boolean isValid(Object value) {
        if (value == null) {
            return !isRequired;
        }

        if (!(value instanceof Integer number)) {
            return false;
        }

        if (!isRequired && number.equals(null)) {
            return true;
        }

        if (isRequired && number.equals(null)) {
            return false;
        }

        if (number <= 0) {
            return !isPositive;
        }

        if (hasRange && (number < start || number > end)) {
            return false;
        }

        return true;
    }
}
