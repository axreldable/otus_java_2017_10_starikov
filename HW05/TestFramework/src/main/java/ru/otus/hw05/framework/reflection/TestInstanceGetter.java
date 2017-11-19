package ru.otus.hw05.framework.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import ru.otus.hw05.framework.annotation.After;
import ru.otus.hw05.framework.annotation.Before;
import ru.otus.hw05.framework.annotation.Test;
import ru.otus.hw05.framework.model.TestInstance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class TestInstanceGetter {
    private Reflections reflections;

    public TestInstanceGetter(String packageName) {
        reflections = new Reflections(packageName, new MethodAnnotationsScanner());
    }

    public Set<TestInstance> getTests() {
        return reflections.getMethodsAnnotatedWith(Test.class).stream()
                .map(testMethod -> TestInstance.builder()
                        .beforeMethods(getBeforeMethods(testMethod))
                        .testMethod(testMethod)
                        .afterMethods(getAfterMethods(testMethod))
                        .build())
                .collect(Collectors.toSet());
    }

    private Set<Method> getAfterMethods(Method testMethod) {
        return getAnnotatedMethods(testMethod, After.class);
    }

    private Set<Method> getBeforeMethods(Method testMethod) {
        return getAnnotatedMethods(testMethod, Before.class);
    }

    private Set<Method> getAnnotatedMethods(Method testMethod, final Class<? extends Annotation> annotation) {
        return Arrays.stream(testMethod.getDeclaringClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toSet());
    }
}
