package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer minSize = 0;
    private Map<String, ? extends BaseSchema<?>> mapSchemas = null;

    public MapSchema sizeof(int size) {
        this.minSize = size;
        return this;
    }

    public void shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.mapSchemas = schemas;
    }

    @Override
    protected Class<Map> getType() {
        return Map.class;
    }

    @Override
    protected boolean validate(Map map) {
        if (map.size() < minSize) {
            return false;
        }

        if (mapSchemas != null) {
            for (Object objEntry : map.entrySet()) {
                Map.Entry entry = (Map.Entry) objEntry;
                Object key = entry.getKey();
                Object value = entry.getValue();
                BaseSchema<?> schema = mapSchemas.get(key);

                if (schema != null && !schema.isValid(value)) {
                    return false;
                }
            }
        }

        return true;
    }
}
