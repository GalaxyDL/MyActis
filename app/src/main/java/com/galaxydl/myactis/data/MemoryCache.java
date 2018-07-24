package com.galaxydl.myactis.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryCache<T extends CacheAble> {

    private Map<String, T> mCache;

    public MemoryCache() {
        mCache = new HashMap<>();
    }

    public void put(T value) {
        mCache.put(value.getId(), value);
    }

    public void putAll(List<T> values) {
        for(T value: values) {
            put(value);
        }
    }

    public T get(String key) {
        return mCache.get(key);
    }

    public List<T> getAll() {
        return new ArrayList<>(mCache.values());
    }
}
