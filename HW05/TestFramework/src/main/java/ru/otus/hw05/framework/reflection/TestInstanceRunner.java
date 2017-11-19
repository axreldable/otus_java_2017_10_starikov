package ru.otus.hw05.framework.reflection;

import ru.otus.hw05.framework.constants.Constants;
import ru.otus.hw05.framework.model.TestInstance;
import ru.otus.hw05.framework.model.result.TestResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import static ru.otus.hw05.framework.model.result.Status.FAILD;
import static ru.otus.hw05.framework.model.result.Status.OK;

public class TestInstanceRunner {
    /**
     * Try to work as junit
     *
     * @param testInstance
     */
    public void run(TestInstance testInstance) {
        Method testMethod = testInstance.getTestMethod();

        TestResult testResult = TestResult.builder()
                .method(testMethod.getName())
                .status(OK)
                .build();

        try {
            Object classInstance = getClassInstanceForMethod(testMethod);
            try {
                // start before methods
                invokeMethods(testInstance.getBeforeMethods(), classInstance);
                try {
                    // if before methods success - start test method
                    invokeMethod(testMethod, classInstance);
                    try {
                        // before and test method - success
                        invokeMethods(testInstance.getAfterMethods(), classInstance);
                    } catch (Exception e1) {
                        testResult.setStatus(FAILD);
                        testResult.setExceptionMessage(Constants.AFTER_EXCEPTION);
                    }
                } catch (InvocationTargetException iex) {
                    // before methods - success, test method - fail
                    testResult.setStatus(FAILD);
                    testResult.setExceptionMessage(iex.getCause().toString());
                    try {
                        invokeMethods(testInstance.getAfterMethods(), classInstance);
                    } catch (Exception e1) {
                        testResult.setStatus(FAILD);
                        testResult.setExceptionMessage(Constants.TEST_AFTER_EXCEPTION);
                    }
                } catch (Exception e) {
                    // before methods - success, test method - fail
                    testResult.setStatus(FAILD);
                    testResult.setExceptionMessage(Constants.TEST_EXCEPTION);
                    try {
                        invokeMethods(testInstance.getAfterMethods(), classInstance);
                    } catch (Exception e1) {
                        testResult.setStatus(FAILD);
                        testResult.setExceptionMessage(Constants.TEST_AFTER_EXCEPTION);
                    }
                }
            } catch (Exception e) {
                testResult.setStatus(FAILD);
                testResult.setExceptionMessage(Constants.BEFORE_EXCEPTION);
                try {
                    invokeMethods(testInstance.getAfterMethods(), classInstance);
                } catch (Exception e1) {
                    testResult.setStatus(FAILD);
                    testResult.setExceptionMessage(Constants.BEFORE_AFTER_EXCEPTION);
                }
            }
            System.out.println(testResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void invokeMethods(Set<Method> methods, Object classInstance) throws Exception {
        for (Method beforeMethod : methods) {
            invokeMethod(beforeMethod, classInstance);
        }
    }

    private Object getClassInstanceForMethod(Method method) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader loader = method.getDeclaringClass().getClassLoader();
        loader.setDefaultAssertionStatus(true);
        Class<?> clazz = Class.forName(method.getDeclaringClass().getName(), true, loader);

        return clazz.newInstance();
    }

    private void invokeMethod(Method method, Object instance) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(instance);
    }
}
