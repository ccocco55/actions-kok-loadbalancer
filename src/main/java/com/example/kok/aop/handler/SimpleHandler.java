package com.example.kok.aop.handler;

import com.example.kok.aop.util.MethodMatcher;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class SimpleHandler implements InvocationHandler {
    private final Object target;
    private final MethodMatcher methodMatcher;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object returnValue = method.invoke(target, args);

        if(returnValue instanceof String && methodMatcher.matches(method)){
            return ((String) returnValue) + "!";
        }

        return returnValue;
    }
}

















