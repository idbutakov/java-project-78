package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StringSchemaTest {

    @Test
    public void testOptionalValidation() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.minLength(5);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    public void testRequiredValidation() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    public void testMinLengthValidation() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        schema.minLength(5);

        assertFalse(schema.isValid("abc"));
        assertTrue(schema.isValid("abcde"));
    }

    @Test
    public void testContainsValidation() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        schema.contains("wh");
        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("what");
        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("whatthe");
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public void testMinLengthOverride() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();

        schema.minLength(10).minLength(4);

        assertTrue(schema.isValid("Hexlet"));
    }
}
