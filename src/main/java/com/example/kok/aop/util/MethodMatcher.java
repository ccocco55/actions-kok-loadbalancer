package com.example.kok.aop.util;

import java.lang.reflect.Method;

public interface MethodMatcher {
    boolean matches(Method method);
}
