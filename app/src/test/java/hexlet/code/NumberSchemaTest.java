package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class NumberSchemaTest {

    @Test
    public void testDefaultValidation() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
    }

    @Test
    public void testPositiveConstraintWithNull() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        assertTrue(schema.positive().isValid(null));
    }

    @Test
    public void testRequiredValidation() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void testPositiveValidation() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        schema.required();
        schema.positive();

        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void testRangeValidation() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        schema.required();
        schema.positive();
        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }
}
