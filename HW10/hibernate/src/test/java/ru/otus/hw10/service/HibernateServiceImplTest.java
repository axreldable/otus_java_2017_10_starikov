package ru.otus.hw10.service;

import org.junit.After;
import org.junit.Test;
import ru.otus.hw10.data.AddressDataSet;
import ru.otus.hw10.data.UserDataSet;

import static org.junit.Assert.*;

public class HibernateServiceImplTest {
    private DBService service;

    @After
    public void tearDown() {
        service.shutdown();
    }

    @Test
    public void hibernateTest() {
        service = new HibernateServiceImpl();
        serviceTest();
    }

    private void serviceTest() {
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
    }

    private int getUserAmount() {
        return service.getAmount();
    }

}