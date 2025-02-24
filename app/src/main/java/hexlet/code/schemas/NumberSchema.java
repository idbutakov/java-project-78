package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addCheck("positive", num -> num > 0);
        return this;
    }

    public NumberSchema range(int a, int b) {
        addCheck("range", num -> num >= a && num <= b);
        return this;
    }

    @Override
    protected Class<Integer> getType() {
        return Integer.class;
    }
}
