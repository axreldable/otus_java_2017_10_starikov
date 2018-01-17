package ru.otus.hw10.service;

import org.junit.After;
import org.junit.Test;
import ru.otus.hw10.data.UserDataSet;

import static org.junit.Assert.*;

public class DBServiceImplTest {
    private DBService service;

    @After
    public void tearDown() {
        service.shutdown();
    }

    @Test
    public void myOrmTest() throws Exception {
        service = new DBServiceImpl();
        serviceTest();
    }

    private void serviceTest() {
        UserDataSet userTo = new UserDataSet("name1", 1);
        assertNull(userTo.getId());

        int startUserAmount = getUserAmount();
        service.save(userTo);
        assertNotNull(userTo.getId());
        assertEquals(startUserAmount + 1, getUserAmount());

        UserDataSet userFrom = service.load(userTo.getId());
        assertEquals(userTo, userFrom);
    }

    private int getUserAmount() {
        return service.getAmount();
    }
}