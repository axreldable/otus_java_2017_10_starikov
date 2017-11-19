package ru.otus.hw05.framework.model;

import lombok.Builder;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Set;

@Builder
@Getter
public class TestInstance {
    private Set<Method> beforeMethods;
    private Method testMethod;
    private Set<Method> afterMethods;
}
