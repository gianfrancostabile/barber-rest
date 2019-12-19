package com.gfstabile.java.barberrest.entity.barber;

import org.junit.Assert;
import org.junit.Test;
import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

public class BarberDTOTest {

    @Test
    public void testAllGettersSettersAndConstructors() {
        Assertions.assertPojoMethodsFor(BarberDTO.class)
            .testing(Method.GETTER, Method.SETTER, Method.CONSTRUCTOR, Method.EQUALS, Method.HASH_CODE)
            .areWellImplemented();
    }

    @Test
    public void toString_AllFieldsWithValues_ReturnsStringAsJsonFormatWithoutNullValues() {
        BarberDTO barberDTO = new BarberDTO("1", "Name", "Surname");
        Assert.assertEquals("toString_AllFieldsWithValues_ReturnsStringAsJsonFormatWithoutNullValues",
            "{\"internalCode\": \"1\", \"name\": \"Name\", \"surname\": \"Surname\"}", barberDTO.toString());
    }

    @Test
    public void toString_AllFieldsAreNull_ReturnsStringAsJsonFormatWithNullValues() {
        BarberDTO barberDTO = new BarberDTO();
        Assert.assertEquals("toString_AllFieldsWithValues_ReturnsStringAsJsonFormatWithoutNullValues",
            "{\"internalCode\": null, \"name\": null, \"surname\": null}", barberDTO.toString());
    }
}
