package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    @Test
    public void testDefaultValidation() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        assertTrue(schema.isValid(null));
    }

    @Test
    public void testRequiredValidation() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(data));
    }

    @Test
    public void testRequiredSizeValidation() {
        Validator v = new Validator();
        MapSchema schema = v.map();
        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        schema.required();
        schema.sizeof(2);
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }
}
