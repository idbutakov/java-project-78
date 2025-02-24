package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    public final NumberSchema required() {
        super.required();
        return this;
    }

    public final NumberSchema positive() {
        addCheck("positive", num -> num > 0);
        return this;
    }

    public final NumberSchema range(int a, int b) {
        addCheck("range", num -> num >= a && num <= b);
        return this;
    }

    @Override
    protected final Class<Integer> getType() {
        return Integer.class;
    }
}
