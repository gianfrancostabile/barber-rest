package com.gfstabile.java.barberrest.exception;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class BarberDoNotExistsExceptionTest {

    @Test
    public void testConstructor() {
        Assertions.assertPojoMethodsFor(BarberDoNotExistsException.class)
            .testing(Method.CONSTRUCTOR)
            .areWellImplemented();
    }
}
