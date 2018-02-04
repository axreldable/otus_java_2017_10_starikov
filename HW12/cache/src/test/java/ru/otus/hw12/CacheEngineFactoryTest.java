package ru.otus.hw12;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CacheEngineFactoryTest {
    @Test
    public void eternalCacheTest() {
        CacheEngine<Integer, Integer> cacheEngine = CacheEngineFactory.create(5);
        cacheEngine.put(new CacheElem<>(1, 1));
        cacheEngine.put(new CacheElem<>(2, 2));
        cacheEngine.put(new CacheElem<>(3, 3));
        cacheEngine.put(new CacheElem<>(4, 4));
        cacheEngine.put(new CacheElem<>(5, 5));
        cacheEngine.put(new CacheElem<>(6, 6));

        assertNull(cacheEngine.get(1));
        assertNotNull(cacheEngine.get(2));
        assertNotNull(cacheEngine.get(3));
        assertNotNull(cacheEngine.get(4));
        assertNotNull(cacheEngine.get(5));
        assertNotNull(cacheEngine.get(6));
        assertNull(cacheEngine.get(7));
    }

    @Test
    public void cacheTest() throws Exception {
        CacheEngine<Integer, Integer> cache = CacheEngineFactory.create(5, 2000, 500);
        cache.put(new CacheElem<>(1, 1));
        cache.put(new CacheElem<>(2, 1));
        cache.put(new CacheElem<>(3, 1));
        cache.put(new CacheElem<>(4, 1));
        cache.put(new CacheElem<>(5, 1));

        assertNotNull(cache.get(1));
        assertNotNull(cache.get(2));
        assertNotNull(cache.get(3));
        assertNotNull(cache.get(4));
        assertNotNull(cache.get(5));

        Thread.sleep(100);
        assertNotNull(cache.get(1));
        assertNotNull(cache.get(2));

        Thread.sleep(450);
        assertNotNull(cache.get(1));
        assertNotNull(cache.get(2));

        assertNull(cache.get(3));
        assertNull(cache.get(4));
        assertNull(cache.get(5));

        Thread.sleep(2100);
        assertNull(cache.get(1));
        assertNull(cache.get(2));
    }
}