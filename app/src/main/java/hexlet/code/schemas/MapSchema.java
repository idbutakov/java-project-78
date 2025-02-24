package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {

    public MapSchema sizeof(int size) {
        addCheck("minSize", m -> m.size() >= size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", m -> {
            for (Map.Entry<String, ? extends BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                if (!schema.isValid(m.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    @Override
    protected Class<Map> getType() {
        return Map.class;
    }
}
