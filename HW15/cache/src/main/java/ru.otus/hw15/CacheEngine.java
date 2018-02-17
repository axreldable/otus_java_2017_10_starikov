package ru.otus.hw15;

public interface CacheEngine<K, V> {
    void put(CacheElem<K, V> element);

    CacheElem<K, V> get(K key);

    int getHitCount();

    int getMissCount();

    int getSize();

    void dispose();
}
