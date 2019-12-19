package com.gfstabile.java.barberrest.controller;

import com.gfstabile.java.barberrest.entity.barber.Barber;
import com.gfstabile.java.barberrest.entity.barber.BarberDTO;
import com.gfstabile.java.barberrest.entity.barber.BarberMapper;
import com.gfstabile.java.barberrest.exception.BarberAlreadyExistsException;
import com.gfstabile.java.barberrest.exception.BarberDoNotExistsException;
import com.gfstabile.java.barberrest.service.BarberService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BarberController.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BarberControllerTest {

    private final String BASE_PATH = "/barber";
    private final String BASE_PATH_WITH_INTERNAL_CODE = BASE_PATH.concat("/{internalCode}");

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BarberService barberService;
    @MockBean
    private BarberMapper barberMapper;

    @Test
    public void insertBarber_SendValidBarber_Returns201StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "Name", "Surname");
        Barber barberMapped = new Barber(0L, "1", "Name", "Surname");
        Mockito.when(this.barberMapper.fromBarberDtoToBarber(barberRequest))
            .thenReturn(barberMapped);
        Mockito.doNothing()
            .when(this.barberService)
            .insertBarber(barberMapped);

        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isCreated());
    }

    @Test
    public void insertBarber_SendExistingBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "Name", "Surname");
        Barber barberMapped = new Barber(0L, "1", "Name", "Surname");
        Mockito.when(this.barberMapper.fromBarberDtoToBarber(barberRequest))
            .thenReturn(barberMapped);
        Mockito.doThrow(BarberAlreadyExistsException.class)
            .when(this.barberService)
            .insertBarber(barberMapped);

        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void insertBarber_SendNullBarber_Returns400StatusCode() throws Exception {
        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void insertBarber_SendNullInternalCodeBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO(null, "Name", "Surname");
        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void insertBarber_SendEmptyInternalCodeBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("", "Name", "Surname");
        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void insertBarber_SendNullNameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", null, "Surname");
        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void insertBarber_SendEmptyNameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "", "Surname");
        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void insertBarber_SendNullSurnameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "Name", null);
        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void insertBarber_SendEmptySurnameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "Name", "");
        this.mockMvc.perform(post(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendExistingBarber_Returns204StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "Name", "Surname");
        Barber barberMapped = new Barber(0L, "1", "Name", "Surname");
        Mockito.when(this.barberMapper.fromBarberDtoToBarber(barberRequest))
            .thenReturn(barberMapped);
        Mockito.doNothing()
            .when(this.barberService)
            .updateBarber(barberMapped);

        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isNoContent());
    }

    @Test
    public void updateBarber_SendNonExistentBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("2", "Name", "Surname");
        Barber barberMapped = new Barber(0L, "2", "Name", "Surname");
        Mockito.when(this.barberMapper.fromBarberDtoToBarber(barberRequest))
            .thenReturn(barberMapped);
        Mockito.doThrow(BarberDoNotExistsException.class)
            .when(this.barberService)
            .updateBarber(barberMapped);

        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendNullBarber_Returns400StatusCode() throws Exception {
        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendNullInternalCodeBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO(null, "Name", "Surname");
        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendEmptyInternalCodeBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("", "Name", "Surname");
        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendNullNameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", null, "Surname");
        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendEmptyNameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "", "Surname");
        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendNullSurnameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "Name", null);
        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void updateBarber_SendEmptySurnameBarber_Returns400StatusCode() throws Exception {
        BarberDTO barberRequest = new BarberDTO("1", "Name", "");
        this.mockMvc.perform(put(this.BASE_PATH).contentType(MediaType.APPLICATION_JSON)
            .content(barberRequest.toString()))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBarber_SendExistingBarberInternalCode_Returns204StatusCode() throws Exception {
        String internalCode = "1";
        Mockito.doNothing()
            .when(this.barberService)
            .deleteBarber(internalCode);
        this.mockMvc.perform(delete(this.BASE_PATH_WITH_INTERNAL_CODE, internalCode))
            .andExpect(status().isNoContent());
    }

    @Test
    public void deleteBarber_SendNonExistentBarberInternalCode_Returns400StatusCode() throws Exception {
        String internalCode = "2";
        Mockito.doThrow(BarberDoNotExistsException.class)
            .when(this.barberService)
            .deleteBarber(internalCode);
        this.mockMvc.perform(delete(this.BASE_PATH_WITH_INTERNAL_CODE, internalCode))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBarber_SendEmptyBarberInternalCode_Returns400StatusCode() throws Exception {
        this.mockMvc.perform(delete(this.BASE_PATH_WITH_INTERNAL_CODE, ""))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void getBarber_SendExistingBarberInternalCode_Returns200StatusCodeWithTheBarber() throws Exception {
        String internalCode = "1";
        BarberDTO expectedBarberDTO = new BarberDTO("1", "Name", "Surname");
        Barber barber = new Barber(1L, "1", "Name", "Surname");
        Mockito.when(this.barberService.getBarber(internalCode))
            .thenReturn(Optional.of(barber));
        Mockito.when(this.barberMapper.fromBarberToBarberDto(barber))
            .thenReturn(expectedBarberDTO);

        this.mockMvc.perform(get(this.BASE_PATH_WITH_INTERNAL_CODE, internalCode))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedBarberDTO.toString()));
    }

    @Test
    public void getBarber_SendNonExistentBarberInternalCode_Returns404StatusCode() throws Exception {
        String internalCode = "2";
        Mockito.when(this.barberService.getBarber(internalCode))
            .thenReturn(Optional.empty());
        this.mockMvc.perform(get(this.BASE_PATH_WITH_INTERNAL_CODE, internalCode))
            .andExpect(status().isNotFound());
    }

    @Test
    public void getBarbers_TwoBarbersAreSaved_Returns200StatusCodeWithTheBarbers() throws Exception {
        Barber barberOne = new Barber(1L, "1", "NameOne", "SurnameOne");
        Barber barberTwo = new Barber(2L, "2", "NameTwo", "SurnameTwo");
        List<Barber> barberListServiceResponse = new ArrayList<>();
        barberListServiceResponse.add(barberOne);
        barberListServiceResponse.add(barberTwo);

        BarberDTO barberDTOOne = new BarberDTO("1", "NameOne", "SurnameOne");
        BarberDTO barberDTOTwo = new BarberDTO("2", "NameTwo", "SurnameTwo");
        List<BarberDTO> expectedBarberDTOList = new ArrayList<>();
        expectedBarberDTOList.add(barberDTOOne);
        expectedBarberDTOList.add(barberDTOTwo);

        Mockito.when(this.barberService.getBarbers())
            .thenReturn(barberListServiceResponse);
        Mockito.when(this.barberMapper.fromBarberToBarberDto(barberOne))
            .thenReturn(barberDTOOne);
        Mockito.when(this.barberMapper.fromBarberToBarberDto(barberTwo))
            .thenReturn(barberDTOTwo);

        this.mockMvc.perform(get(this.BASE_PATH))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedBarberDTOList.toString()));
    }

    @Test
    public void getBarbers_NoBarbersSaved_Returns404StatusCodeWithEmptyArray() throws Exception {
        Mockito.when(this.barberService.getBarbers())
            .thenReturn(new ArrayList<>());

        this.mockMvc.perform(get(this.BASE_PATH))
            .andExpect(status().isNotFound())
            .andExpect(content().json("[]"));
    }
}
