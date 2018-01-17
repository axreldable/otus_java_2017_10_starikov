package ru.otus.hw10.query;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static ru.otus.hw10.sql.query.SqlQueryCreator.createInsert;
import static ru.otus.hw10.sql.query.SqlQueryCreator.createSelect;

public class SqlQueryCreatorTest {
    private final static String TABLE = "TABLE";

    @Test
    public void selectTest() {
        String select = createSelect(TABLE, null, 1L);
        assertEquals("select * from TABLE where id = 1", select);

        select = createSelect(TABLE, Collections.singletonList("column1"), 1L);
        assertEquals("select column1 from TABLE where id = 1", select);

        select = createSelect(TABLE, Arrays.asList("column1", "column2"), 1L);
        assertEquals("select column1,column2 from TABLE where id = 1", select);

        select = createSelect(TABLE);
        assertEquals("select * from TABLE", select);
    }

    @Test
    public void insertTest() {
        String insert = createInsert(TABLE, null);
        assertEquals("insert into TABLE() values()", insert);


        insert = createInsert(TABLE, new HashMap<String, Object>() {
            {
                put("column1", "value1");
            }
        });
        assertEquals("insert into TABLE(column1) values('value1')", insert);

        insert = createInsert(TABLE, new HashMap<String, Object>() {
            {
                put("column1", null);
            }
        });
        assertEquals("insert into TABLE(column1) values(NULL)", insert);

        insert = createInsert(TABLE, new HashMap<String, Object>() {
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