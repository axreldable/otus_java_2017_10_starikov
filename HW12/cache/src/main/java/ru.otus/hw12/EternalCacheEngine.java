package ru.otus.hw12;

import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.Map;

public class EternalCacheEngine<K, V> implements CacheEngine<K, V> {
    private final int maxElements;
    final Map<K, SoftReference<CacheElem<K, V>>> elements = new LinkedHashMap<>();
    int hit = 0;
    int miss = 0;

    public EternalCacheEngine(int maxElements) {
        this.maxElements = maxElements;
    }

    public void put(CacheElem<K, V> element) {
        if (elements.size() == maxElements) {
            removeFirstElem();

        }
        elements.put(element.getKey(), new SoftReference<>(element));
    }

    private void removeFirstElem() {
        K firstKey = elements.keySet().iterator().next();
        elements.remove(firstKey);
    }

    public CacheElem<K, V> get(K key) {
        if (elements.containsKey(key)) {
            CacheElem<K, V> elem = elements.get(key).get();
            if (elem != null) {
                hit++;
                return elem;
            }
        }
        miss++;
        return null;
    }

    public int getHitCount() {
        return hit;
    }

    public int getMissCount() {
        return miss;
    }

    @Override
    public int getSize() {
        return maxElements;
    }

    @Override
    public void dispose() {}
}
