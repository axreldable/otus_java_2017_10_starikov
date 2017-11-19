package ru.otus.hw05.framework.model.result;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class TestResult {
    private String method;
    private Status status;
    private String exceptionMessage;

    @Override
    public String toString() {
        String exception = exceptionMessage == null ? "" : ", exceptionMessage='" + exceptionMessage + '\'';
        return "TestResult{" +
                "method='" + method + '\'' +
                ", status=" + status +
                exception +
                '}' + "\n";
    }
}
