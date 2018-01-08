package ru.otus.hw8.json.my.simple.json;

import java.util.List;

import static ru.otus.hw8.json.my.simple.json.Constants.NULL;

public class MyJsonValue {
    static void escape(String s, StringBuffer sb) {
        for(int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            switch(ch) {
                case '\b':
                    sb.append("\\b");
                    continue;
                case '\t':
                    sb.append("\\t");
                    continue;
                case '\n':
                    sb.append("\\n");
                    continue;
                case '\f':
                    sb.append("\\f");
                    continue;
                case '\r':
                    sb.append("\\r");
                    continue;
                case '"':
                    sb.append("\\\"");
                    continue;
                case '/':
                    sb.append("\\/");
                    continue;
                case '\\':
                    sb.append("\\\\");
                    continue;
            }

            if (ch >= 0 && ch <= 31 || ch >= 127 && ch <= 159 || ch >= 8192 && ch <= 8447) {
                String ss = Integer.toHexString(ch);
                sb.append("\\u");

                for(int k = 0; k < 4 - ss.length(); ++k) {
                    sb.append('0');
                }

                sb.append(ss.toUpperCase());
            } else {
                sb.append(ch);
            }
        }
    }

    public static String toJsonString(Object value) {
        if (value == null) {
            return NULL;
        } else if (value instanceof String) {
            return "\"" + escape((String)value) + "\"";
        } else if (value instanceof Double) {
            return !((Double)value).isInfinite() && !((Double)value).isNaN() ? value.toString() : NULL;
        } else if (value instanceof Float) {
            return !((Float)value).isInfinite() && !((Float)value).isNaN() ? value.toString() : NULL;
        } else if (value instanceof Number) {
            return value.toString();
        } else if (value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof MyJsonAware) {
            return ((MyJsonAware)value).toJsonString();
        } else {
            return value instanceof List ? MyJsonArray.toJsonString((List)value) : value.toString();
        }
    }

    public static String escape(String s) {
        if (s == null) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            escape(s, sb);
            return sb.toString();
        }
    }
}
