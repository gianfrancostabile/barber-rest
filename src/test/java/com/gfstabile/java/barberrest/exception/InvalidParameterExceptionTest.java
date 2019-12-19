package com.gfstabile.java.barberrest.exception;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class InvalidParameterExceptionTest {

    @Test
    public void testConstructor() {
        Assertions.assertPojoMethodsFor(InvalidParameterException.class)
            .testing(Method.CONSTRUCTOR)
            .areWellImplemented();
    }
}
