package ru.otus.hw11;

public class CacheEngineFactory {
    public static <K, V> CacheEngine<K, V> create(int maxElements, long lifeTimeMs, long idleTimeMs) {
        if (lifeTimeMs == 0 && idleTimeMs == 0) {
            return create(maxElements);
        } else  {
            return new CacheEngineImpl<>(maxElements, lifeTimeMs, idleTimeMs);
        }
    }

    public static <K, V> CacheEngine<K, V> create(int maxElements) {
        return new EternalCacheEngine<>(maxElements);
    }
}
