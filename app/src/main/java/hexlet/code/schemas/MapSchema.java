package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map> {
    private Integer minSize = 0;

    public MapSchema sizeof(int size) {
        this.minSize = size;
        return this;
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
        return true;
    }
}
