package com.gfstabile.java.barberrest.service;

import com.gfstabile.java.barberrest.entity.barber.Barber;
import com.gfstabile.java.barberrest.exception.BarberAlreadyExistsException;
import com.gfstabile.java.barberrest.exception.BarberDoNotExistsException;
import com.gfstabile.java.barberrest.exception.InvalidBarberException;
import com.gfstabile.java.barberrest.exception.InvalidParameterException;
import com.gfstabile.java.barberrest.repository.BarberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
public class BarberServiceTest {

    @Mock
    private BarberRepository barberRepository;

    @InjectMocks
    private BarberService barberService;

    @Test
    public void insertBarber_SendValidBarber_DoNotThrowException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        Barber dummyBarber = new Barber(0L, "1", "Name", "Surname");
        Mockito.when(this.barberRepository.save(Mockito.any(Barber.class)))
            .thenReturn(dummyBarber);
        this.barberService.insertBarber(dummyBarber);
        Assert.assertTrue("insertBarber_SendValidBarber_DoNotThrowException", true);
    }

    @Test(expected = BarberAlreadyExistsException.class)
    public void insertBarber_SendAnExistingBarber_DoNotThrowException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        Barber dummyBarber = new Barber(0L, "1", "Name", "Surname");
        Mockito.when(this.barberRepository.save(Mockito.any(Barber.class)))
            .thenThrow(RuntimeException.class);
        this.barberService.insertBarber(dummyBarber);
        Assert.fail("insertBarber_SendAnExistingBarber_DoNotThrowException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendNullBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(null);
        Assert.fail("insertBarber_SendNullBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendNullCodeBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(new Barber(null, "1", "Name", "Surname"));
        Assert.fail("insertBarber_SendInvalidBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendNullInternalCodeBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(new Barber(0L, null, "Name", "Surname"));
        Assert.fail("insertBarber_SendNullInternalCodeBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendEmptyInternalCodeBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(new Barber(0L, "", "Name", "Surname"));
        Assert.fail("insertBarber_SendEmptyInternalCodeBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendNullNameBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(new Barber(0L, "1", null, "Surname"));
        Assert.fail("insertBarber_SendNullNameBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendEmptyNameBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(new Barber(0L, "1", "", "Surname"));
        Assert.fail("insertBarber_SendEmptyNameBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendNullSurnameBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(new Barber(0L, "1", "Name", null));
        Assert.fail("insertBarber_SendNullSurnameBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void insertBarber_SendEmptySurnameBarber_ThrowInvalidBarberException()
        throws BarberAlreadyExistsException, InvalidBarberException {
        this.barberService.insertBarber(new Barber(0L, "1", "Name", ""));
        Assert.fail("insertBarber_SendEmptySurnameBarber_ThrowInvalidBarberException");
    }

    @Test
    public void updateBarber_SendValidBarberWithExistingInternalCode_DoNotThrowException()
        throws InvalidBarberException, BarberDoNotExistsException {
        Barber dummyBarber = new Barber(0L, "1", "NewName", "NewSurname");
        Mockito.doNothing()
            .when(this.barberRepository)
            .updateBarber("1", "NewName", "NewSurname");
        this.barberService.updateBarber(dummyBarber);
        Assert.assertTrue("updateBarber_SendValidBarberWithSameInternalCode_DoNotThrowException", true);
    }

    @Test(expected = BarberDoNotExistsException.class)
    public void updateBarber_SendValidBarberWithNonExistentInternalCode_DoNotThrowException()
        throws InvalidBarberException, BarberDoNotExistsException {
        Barber dummyBarber = new Barber(0L, "2", "NewName", "NewSurname");
        Mockito.doThrow(RuntimeException.class)
            .when(this.barberRepository)
            .updateBarber("2", "NewName", "NewSurname");
        this.barberService.updateBarber(dummyBarber);
        Assert.fail("updateBarber_SendValidBarberWithNonExistentInternalCode_DoNotThrowException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendNullBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(null);
        Assert.fail("updateBarber_SendNullBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendNullCodeBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(new Barber(null, "1", "NewName", "NewSurname"));
        Assert.fail("updateBarber_SendNullCodeBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendNullInternalCodeBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(new Barber(0L, null, "NewName", "NewSurname"));
        Assert.fail("updateBarber_SendNullInternalCodeBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendEmptyInternalCodeBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(new Barber(0L, "", "NewName", "NewSurname"));
        Assert.fail("updateBarber_SendEmptyInternalCodeBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendNullNameBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(new Barber(0L, "1", null, "NewSurname"));
        Assert.fail("updateBarber_SendNullNameBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendEmptyNameBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(new Barber(0L, "1", "", "NewSurname"));
        Assert.fail("updateBarber_SendEmptyNameBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendNullSurnameBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(new Barber(0L, "1", "NewName", null));
        Assert.fail("updateBarber_SendNullSurnameBarber_ThrowInvalidBarberException");
    }

    @Test(expected = InvalidBarberException.class)
    public void updateBarber_SendEmptySurnameBarber_ThrowInvalidBarberException()
        throws InvalidBarberException, BarberDoNotExistsException {
        this.barberService.updateBarber(new Barber(0L, "1", "NewName", ""));
        Assert.fail("updateBarber_SendEmptySurnameBarber_ThrowInvalidBarberException");
    }

    @Test
    public void deleteBarber_SendExistingBarberInternalCode_DoNotThrowException()
        throws InvalidParameterException, BarberDoNotExistsException {
        String dummyInternalCode = "1";
        Mockito.when(this.barberRepository.findByInternalCode(dummyInternalCode))
            .thenReturn(Optional.of(new Barber()));
        Mockito.doNothing()
            .when(this.barberRepository)
            .deleteByInternalCode(dummyInternalCode);
        this.barberService.deleteBarber(dummyInternalCode);
        Assert.assertTrue("deleteBarber_SendExistingBarberInternalCode_DoNotThrowException", true);
    }

    @Test(expected = BarberDoNotExistsException.class)
    public void deleteBarber_SendNonExistingBarberInternalCode_ThrowBarberDoNotExistsException()
        throws InvalidParameterException, BarberDoNotExistsException {
        String dummyInternalCode = "2";
        Mockito.when(this.barberRepository.findByInternalCode(dummyInternalCode))
            .thenReturn(Optional.empty());
        this.barberService.deleteBarber(dummyInternalCode);
        Assert.fail("deleteBarber_SendNonExistingBarberInternalCode_ThrowBarberDoNotExistsException");
    }

    @Test(expected = InvalidParameterException.class)
    public void deleteBarber_SendNullBarberInternalCode_ThrowInvalidParameterException()
        throws InvalidParameterException, BarberDoNotExistsException {
        this.barberService.deleteBarber(null);
        Assert.fail("deleteBarber_SendNullBarberInternalCode_ThrowInvalidParameterException");
    }

    @Test(expected = InvalidParameterException.class)
    public void deleteBarber_SendEmptyBarberInternalCode_ThrowInvalidParameterException()
        throws InvalidParameterException, BarberDoNotExistsException {
        this.barberService.deleteBarber("");
        Assert.fail("deleteBarber_SendEmptyBarberInternalCode_ThrowInvalidParameterException");
    }

    @Test
    public void getBarber_SendExistingBarberInternalCode_ReturnsAnOptionalWithBarberInformation()
        throws InvalidParameterException {
        String dummyInternalCode = "1";
        Barber dummyBarber = new Barber(1L, dummyInternalCode, "Name", "Surname");
        Mockito.when(this.barberRepository.findByInternalCode(dummyInternalCode))
            .thenReturn(Optional.of(dummyBarber));
        Optional<Barber> actualOptionalBarber = this.barberService.getBarber(dummyInternalCode);

        if (actualOptionalBarber.isPresent()) {
            Assert.assertEquals("getBarber_SendExistingBarberInternalCode_ReturnsAnOptionalWithBarberInformation",
                dummyBarber, actualOptionalBarber.get());
        } else {
            Assert.fail(
                "getBarber_SendExistingBarberInternalCode_ReturnsAnOptionalWithBarberInformation barber should be present but it's not");
        }
    }

    @Test
    public void getBarber_SendNonExistingBarberInternalCode_ReturnsAnEmptyOptional() throws InvalidParameterException {
        String dummyInternalCode = "2";
        Mockito.when(this.barberRepository.findByInternalCode(dummyInternalCode))
            .thenReturn(Optional.empty());
        Optional<Barber> actualOptionalBarber = this.barberService.getBarber(dummyInternalCode);
        Assert.assertFalse("getBarber_SendNonExistingBarberInternalCode_ReturnsAnEmptyOptional",
            actualOptionalBarber.isPresent());
    }

    @Test(expected = InvalidParameterException.class)
    public void getBarber_SendNullBarberInternalCode_ThrowInvalidParameterException() throws InvalidParameterException {
        this.barberService.getBarber(null);
        Assert.fail("getBarber_SendNullBarberInternalCode_ThrowInvalidParameterException");
    }

    @Test(expected = InvalidParameterException.class)
    public void getBarber_SendEmptyBarberInternalCode_ThrowInvalidParameterException()
        throws InvalidParameterException {
        this.barberService.getBarber("");
        Assert.fail("getBarber_SendEmptyBarberInternalCode_ThrowInvalidParameterException");
    }

    @Test
    public void getBarbers_TwoBarbersAreSaved_ReturnsListWithTwoBarbers() throws InvalidParameterException {
        List<Barber> expectedBarberList = new ArrayList<>();
        expectedBarberList.add(new Barber(1L, "1", "Name", "Surname"));
        expectedBarberList.add(new Barber(2L, "2", "AnotherName", "AnotherSurname"));
        Mockito.when(this.barberRepository.findAll())
            .thenReturn(expectedBarberList);
        List<Barber> actualBarberList = this.barberService.getBarbers();
        Assert.assertEquals("getBarbers_TwoBarbersAreSaved_ReturnsListWithTwoBarbers", expectedBarberList,
            actualBarberList);
    }

    @Test
    public void getBarbers_WithoutBarbersSaved_ReturnsEmptyList() throws InvalidParameterException {
        List<Barber> expectedBarberList = new ArrayList<>();
        Mockito.when(this.barberRepository.findAll())
            .thenReturn(expectedBarberList);
        List<Barber> actualBarberList = this.barberService.getBarbers();
        Assert.assertEquals("getBarbers_WithoutBarbersSaved_ReturnsEmptyList", expectedBarberList, actualBarberList);
    }
}
