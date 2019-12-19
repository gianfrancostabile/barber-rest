package com.gfstabile.java.barberrest.exception;

import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class BarberAlreadyExistsExceptionTest {

    @Test
    public void testConstructor() {
        Assertions.assertPojoMethodsFor(BarberAlreadyExistsExceptionTest.class)
            .testing(Method.CONSTRUCTOR)
            .areWellImplemented();
    }
}
