package com.gfstabile.java.barberrest.entity.barber;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class)
public class BarberMapperTest {

    private BarberMapper barberMapper;

    @Before
    public void setUp() {
        this.barberMapper = new BarberMapper();
    }

    @Test
    public void fromBarberDtoToBarber_ReturnBarberInstanceWithTheCorrectValues_SendBarberDto() {
        BarberDTO barberDTO = new BarberDTO("1", "Name", "Surname");
        Barber expectedValue = new Barber(0L, "1", "Name", "Surname");
        Barber actualValue = this.barberMapper.fromBarberDtoToBarber(barberDTO);

        Assert.assertEquals("fromBarberDtoToBarber_ReturnBarberInstanceWithTheCorrectValues_SendBarberDto",
            expectedValue, actualValue);
    }

    @Test
    public void fromBarberDtoToBarber_ReturnNull_SendNullBarber() {
        Barber actualValue = this.barberMapper.fromBarberDtoToBarber(null);

        Assert.assertNull("fromBarberDtoToBarber_ReturnNull_SendNullBarber", actualValue);
    }

    @Test
    public void fromBarberToBarberDto_ReturnBarberDTOInstanceWithTheCorrectValues_SendBarber() {
        Barber barber = new Barber(1L, "1", "Name", "Surname");
        BarberDTO expectedValue = new BarberDTO("1", "Name", "Surname");
        BarberDTO actualValue = this.barberMapper.fromBarberToBarberDto(barber);

        Assert.assertEquals("fromBarberToBarberDto_ReturnBarberDTOInstanceWithTheCorrectValues_SendBarber",
            expectedValue, actualValue);
    }

    @Test
    public void fromBarberToBarberDto_ReturnNull_SendNullBarber() {
        BarberDTO actualValue = this.barberMapper.fromBarberToBarberDto(null);

        Assert.assertNull("fromBarberToBarberDto_ReturnNull_SendNullBarber", actualValue);
    }
}
