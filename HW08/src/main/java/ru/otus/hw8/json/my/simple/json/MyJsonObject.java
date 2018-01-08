package ru.otus.hw8.json.my.simple.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static ru.otus.hw8.json.my.simple.json.Constants.*;
import static ru.otus.hw8.json.my.simple.json.MyJsonValue.escape;

public class MyJsonObject extends HashMap implements MyJsonAware {
    @Override
    public String toJsonString() {
        return toJsonString(this);
    }

    private String toJsonString(Map map) {
        if (map == null) {
            return NULL;
        } else {
            StringBuffer sb = new StringBuffer();
            boolean first = true;
            Iterator iter = map.entrySet().iterator();
            sb.append(BRACE);

            while(iter.hasNext()) {
                if (first) {
                    first = false;
                } else {
                    sb.append(COMMA);
                }

                Entry entry = (Entry)iter.next();
                toJsonString(String.valueOf(entry.getKey()), entry.getValue(), sb);
            }

            sb.append(CLOSING_BRACE);
            return sb.toString();
        }
    }

    private static void toJsonString(String key, Object value, StringBuffer sb) {
        sb.append(DOUBLE_APOSTROPHE);
        if (key == null) {
            sb.append(NULL);
        } else {
            escape(key, sb);
        }

        sb.append(DOUBLE_APOSTROPHE).append(BINARY);
        sb.append(MyJsonValue.toJsonString(value));
    }
}
