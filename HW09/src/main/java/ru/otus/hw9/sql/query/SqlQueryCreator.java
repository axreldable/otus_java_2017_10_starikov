package ru.otus.hw9.sql.query;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Create sql queries.
 */
public class SqlQueryCreator {
    private static final String STAR = "*";
    private static final char COMMA = ',';
    private static final String EMPTY_STRING = "";

    public String select(String tableName, List<String> values) {
        return "select " + joinValues(values, COMMA, STAR) + " from " + tableName;
    }

    public String insert(String tableName, Map<String, Object> columnValueMap) {
        String columnString = EMPTY_STRING;
        String valuesString = EMPTY_STRING;

        if (columnValueMap != null) {
            List<ColumnValuePair> pairs = getColumnValuePairs(columnValueMap);

            columnString = joinValues(getStringArray(pairs, ColumnValuePair::getColumnName), COMMA, EMPTY_STRING);
            valuesString = joinValues(getStringArray(pairs, ColumnValuePair::getSqlValue), COMMA, EMPTY_STRING);
        }

        return "insert into " + tableName + "(" + columnString + ") values(" + valuesString + ")";
    }

    private List getStringArray(List<ColumnValuePair> pairs, Function<ColumnValuePair, String> f) {
        return pairs.stream().map(f).collect(Collectors.toList());
    }

    private String joinValues(List list, char delimiter, String defaultValue) {
        if (list == null) return defaultValue;
        return StringUtils.join(list, delimiter);
    }

    private List<ColumnValuePair> getColumnValuePairs(Map<String, Object> columnValueMap) {
        if (columnValueMap == null) return null;
        return columnValueMap.keySet()
                .stream()
                .map(columnName -> new ColumnValuePair(columnName, columnValueMap.get(columnName)))
                .collect(Collectors.toList());
    }
}
