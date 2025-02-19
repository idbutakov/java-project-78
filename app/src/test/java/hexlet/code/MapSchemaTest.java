package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void testMultipleSchemasStringValidation() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }

    @Test
    public void testMultipleSchemasNumberValidation() {
        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<Integer>> schemas = new HashMap<>();

        schemas.put("height", v.number().positive());
        schemas.put("weight", v.number().required().range(5, 10));

        schema.shape(schemas);

        Map<String, Integer> human1 = new HashMap<>();
        human1.put("height", 15);
        human1.put("weight", 7);
        assertTrue(schema.isValid(human1));

        Map<String, Integer> human2 = new HashMap<>();
        human2.put("height", 15);
        human2.put("weight", null);
        assertFalse(schema.isValid(human2));

        Map<String, Integer> human3 = new HashMap<>();
        human3.put("height", -15);
        human3.put("weight", 7);
        assertFalse(schema.isValid(human3));

        Map<String, Integer> human4 = new HashMap<>();
        human4.put("height", 15);
        human4.put("weight", 17);
        assertFalse(schema.isValid(human4));
    }
}
