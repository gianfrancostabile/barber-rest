package com.gfstabile.java.barberrest.entity.barber;

import org.junit.Assert;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class BarberTest {

    @Test
    public void testAllGettersSettersAndConstructors() {
        Assertions.assertPojoMethodsFor(Barber.class)
            .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.EQUALS, Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void toString_AllFieldsWithValues_ReturnsStringAsJsonFormatWithoutNullValues() {
        Barber barber = new Barber(1L, "1", "Name", "Surname");
        Assert.assertEquals("toString_AllFieldsWithValues_ReturnsStringAsJsonFormatWithoutNullValues",
            "{\"code\": 1, \"internalCode\": \"1\", \"name\": \"Name\", \"surname\": \"Surname\"}", barber.toString());
    }

    @Test
    public void toString_AllFieldsAreNull_ReturnsStringAsJsonFormatWithNullValues() {
        Barber barber = new Barber();
        Assert.assertEquals("toString_AllFieldsWithValues_ReturnsStringAsJsonFormatWithoutNullValues",
            "{\"code\": null, \"internalCode\": null, \"name\": null, \"surname\": null}", barber.toString());
    }
}
