package ru.otus.hw05.framework.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import ru.otus.hw05.framework.annotation.After;
import ru.otus.hw05.framework.annotation.Before;
import ru.otus.hw05.framework.annotation.Test;
import ru.otus.hw05.framework.model.TestInstance;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class TestInstanceGetter {
    private Reflections reflections;

    public TestInstanceGetter(String packageName) {
        reflections = new Reflections(packageName, new MethodAnnotationsScanner());
    }

    public Set<TestInstance> getTests() {
        Set<Method> testMethods = reflections.getMethodsAnnotatedWith(Test.class);

        Set<TestInstance> testInstances = new HashSet<TestInstance>();

        for (Method testMethod : testMethods) {
            testInstances.add(
                    TestInstance.builder()
                            .beforeMethods(getBeforeMethods(testMethod))
                            .testMethod(testMethod)
                            .afterMethods(getAfterMethods(testMethod))
                            .build()
            );
        }
        return testInstances;
    }

    private Set<Method> getAfterMethods(Method testMethod) {
        return getAnnotatedMethods(testMethod, After.class);
    }

    private Set<Method> getBeforeMethods(Method testMethod) {
        return getAnnotatedMethods(testMethod, Before.class);
    }

    private Set<Method> getAnnotatedMethods(Method testMethod, final Class<? extends Annotation> annotation) {
        Set<Method> annotatedMethods = new HashSet<Method>();

        for (Method method : testMethod.getDeclaringClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }
}
