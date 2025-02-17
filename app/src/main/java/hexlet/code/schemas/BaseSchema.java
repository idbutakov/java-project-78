package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    protected boolean isRequired = false;
    protected final Class<T> typeClass;

    protected BaseSchema(Class<T> typeClass) {
        this.typeClass = typeClass;
    }

    public boolean isValid(Object value) {
        return true;
    }
}
