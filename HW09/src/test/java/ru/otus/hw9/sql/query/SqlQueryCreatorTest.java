package ru.otus.hw9.sql.query;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SqlQueryCreatorTest {
    private final static String TABLE = "TABLE";

    private SqlQueryCreator creator;

    @Before
    public void init() {
        creator = new SqlQueryCreator();
    }

    @Test
    public void selectTest() {
        String select = creator.select(TABLE, null);
        assertEquals("select * from TABLE", select);

        select = creator.select(TABLE, Collections.singletonList("column1"));
        assertEquals("select column1 from TABLE", select);

        select = creator.select(TABLE, Arrays.asList("column1", "column2"));
        assertEquals("select column1,column2 from TABLE", select);
    }

    @Test
    public void insertTest() {
        String insert = creator.insert(TABLE, null);
        assertEquals("insert into TABLE() values()", insert);


        insert = creator.insert(TABLE, new HashMap<String, Object>() {
            {
                put("column1", "value1");
            }
        });
        assertEquals("insert into TABLE(column1) values('value1')", insert);

        insert = creator.insert(TABLE, new HashMap<String, Object>() {
            {
                put("column1", null);
            }
        });
        assertEquals("insert into TABLE(column1) values(NULL)", insert);

        insert = creator.insert(TABLE, new HashMap<String, Object>() {
            {
                put("column1", "value1");
                put("column2", 2);
                put("column3", "value3");
                put("column4", null);
            }
        });
        System.out.println(insert); // check it manually
    }
}