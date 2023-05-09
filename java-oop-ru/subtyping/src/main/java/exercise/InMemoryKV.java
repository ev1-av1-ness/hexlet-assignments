package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage{
    private final Map<String, String> storage;

    public InMemoryKV(Map<String, String> data) {
        this.storage = new HashMap<>();
        this.storage.putAll(data);
    }

    @Override
    public void set(String key, String value) {
        this.storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(this.storage);
    }
}
// END
