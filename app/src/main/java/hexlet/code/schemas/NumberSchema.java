package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean isPositive = false;
    private boolean hasRange = false;
    private Integer start = 0;
    private Integer end = 0;

    @Override
    public final NumberSchema required() {
        super.required();
        return this;
    }

    public final NumberSchema positive() {
        isPositive = true;
        return this;
    }

    public final NumberSchema range(int a, int b) {
        this.start = a;
        this.end = b;
        hasRange = true;
        return this;
    }

    @Override
    protected final Class<Integer> getType() {
        return Integer.class;
    }

    @Override
    protected final boolean validate(Integer number) {
        if (isPositive && number <= 0) {
            return false;
        }

        if (hasRange && (number < start || number > end)) {
            return false;
        }
        return true;
    }
}
