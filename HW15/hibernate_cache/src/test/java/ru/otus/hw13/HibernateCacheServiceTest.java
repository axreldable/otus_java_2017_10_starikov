package ru.otus.hw13;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import ru.otus.hw15.data.AddressDataSet;
import ru.otus.hw15.data.UserDataSet;
import ru.otus.hw15.service.DBService;
import ru.otus.hw15.HibernateCacheService;

import static org.junit.Assert.*;
import static ru.otus.hw15.Constants.CACHE_LIFE_TIME;

public class HibernateCacheServiceTest {
    private DBService service;

    @After
    public void tearDown() {
        service.shutdown();
    }

    @Test
    @Ignore
    public void hibernateTest() throws InterruptedException {
        service = new HibernateCacheService();
        serviceTest();
    }

    private void serviceTest() throws InterruptedException {
        UserDataSet userTo = new UserDataSet("name1", 1, new AddressDataSet("some street"));
        assertEquals(-1L, (long) userTo.getId());
        assertNull(userTo.getAddress().getId());

        int startUserAmount = getUserAmount();
        service.save(userTo);
        assertNotEquals(-1L, (long) userTo.getId());
        assertNotEquals(-1L, (long) userTo.getAddress().getId());
        assertEquals(startUserAmount + 1, getUserAmount());

        UserDataSet userFrom = service.load(userTo.getId());
        assertEquals(userTo, userFrom);

        testCache(userTo, userFrom);
    }

    private void testCache(UserDataSet userTo, UserDataSet userFrom) throws InterruptedException {
        HibernateCacheService cacheService = (HibernateCacheService) service;
        assertEquals(userTo, cacheService.getCache().get(userTo.getId()).getValue());
        assertEquals(userFrom, cacheService.getCache().get(userFrom.getId()).getValue());

        Thread.sleep(CACHE_LIFE_TIME + 1000); // 30 sec...

        assertNull(cacheService.getCache().get(userTo.getId()));
        assertNull(cacheService.getCache().get(userFrom.getId()));
    }

    private int getUserAmount() {
        return service.getAmount();
    }
}