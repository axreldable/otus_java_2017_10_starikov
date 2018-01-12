package ru.otus.hw9.sql.query;

public class ColumnValuePair {
    private static final String APOSTROPHE = "'";
    private static final String NULL = "NULL";

    private final String columnName;
    private final Object value;

    public ColumnValuePair(String columnName, Object value) {
        this.columnName = columnName;
        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getSqlValue() {
        if (value == null) return NULL;
        return APOSTROPHE + value + APOSTROPHE;
    }
}
