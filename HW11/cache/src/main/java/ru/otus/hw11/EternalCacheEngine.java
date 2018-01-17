package ru.otus.hw11;

import java.util.LinkedHashMap;
import java.util.Map;

public class EternalCacheEngine<K, V> implements CacheEngine<K, V> {
    private final int maxElements;
    final Map<K, CacheElem<K, V>> elements = new LinkedHashMap<>();
    int hit = 0;
    int miss = 0;

    public EternalCacheEngine(int maxElements) {
        this.maxElements = maxElements;
    }

    public void put(CacheElem<K, V> element) {
        if (elements.size() == maxElements) {
            removeFirstElem();

        }
        elements.put(element.getKey(), element);
    }

    private void removeFirstElem() {
        K firstKey = elements.keySet().iterator().next();
        elements.remove(firstKey);
    }

    public CacheElem<K, V> get(K key) {
        CacheElem<K, V> element = elements.get(key);
        if (element != null) {
            hit++;
        } else {
            miss++;
        }
        return element;
    }

    public int getHitCount() {
        return hit;
    }

    public int getMissCount() {
        return miss;
    }

    @Override
    public void dispose() {}
}
