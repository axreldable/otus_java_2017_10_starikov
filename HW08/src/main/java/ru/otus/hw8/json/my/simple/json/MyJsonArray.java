package ru.otus.hw8.json.my.simple.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static ru.otus.hw8.json.my.simple.json.Constants.*;

public class MyJsonArray extends ArrayList implements MyJsonAware {
    @Override
    public String toJsonString() {
        return toJsonString(this);
    }

    public static String toJsonString(List list) {
        if (list == null) {
            return NULL;
        } else {
            boolean first = true;
            StringBuffer sb = new StringBuffer();
            Iterator iter = list.iterator();
            sb.append(SQUARE_BRACKET);

            while(iter.hasNext()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(COMMA);
                }

                Object value = iter.next();
                if (value == null) {
                    sb.append(NULL);
                } else {
                    sb.append(MyJsonValue.toJsonString(value));
                }
            }

            sb.append(CLOSING_SQUARE_BRACKET);
            return sb.toString();
        }
    }
}
