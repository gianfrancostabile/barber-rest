package com.gfstabile.java.barberrest.controller;

import com.gfstabile.java.barberrest.entity.barber.Barber;
import com.gfstabile.java.barberrest.entity.barber.BarberDTO;
import com.gfstabile.java.barberrest.entity.barber.BarberMapper;
import com.gfstabile.java.barberrest.exception.BarberAlreadyExistsException;
import com.gfstabile.java.barberrest.exception.BarberDoNotExistsException;
import com.gfstabile.java.barberrest.exception.InvalidBarberException;
import com.gfstabile.java.barberrest.exception.InvalidParameterException;
import com.gfstabile.java.barberrest.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The bean controller related to Barber entity
 *
 * @author G. F. Stabile
 */
@RestController
@RequestMapping(value = "/barber")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @Autowired
    private BarberMapper barberMapper;

    /**
     * REST POST method is responsible to insert a new barber.
     *
     * @param barberDTO the new barber
     * @return 200 if the barber was successfully inserted; otherwise 400
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> insertBarber(@RequestBody @Valid BarberDTO barberDTO) {
        HttpStatus responseCode;
        try {
            this.barberService.insertBarber(this.barberMapper.fromBarberDtoToBarber(barberDTO));
            responseCode = HttpStatus.CREATED;
        } catch (BarberAlreadyExistsException | InvalidBarberException exception) {
            responseCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseCode);
    }

    /**
     * REST PUT method is responsible to update an existing barber.
     *
     * @param barberDTO the new barber values
     * @return 204 if the barber was successfully updated; otherwise 400
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateBarber(@RequestBody @Valid BarberDTO barberDTO) {
        HttpStatus responseCode;
        try {
            this.barberService.updateBarber(this.barberMapper.fromBarberDtoToBarber(barberDTO));
            responseCode = HttpStatus.NO_CONTENT;
        } catch (BarberDoNotExistsException | InvalidBarberException exception) {
            responseCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseCode);
    }

    /**
     * REST PUT method is responsible to delete an existing barber.
     *
     * @param internalCode the barber internal code to delete
     * @return 204 if the barber was successfully deleted; otherwise 400
     */
    @DeleteMapping("/{internalCode}")
    public ResponseEntity<Void> deleteBarber(@PathVariable @NotBlank String internalCode) {
        HttpStatus responseCode;
        try {
            this.barberService.deleteBarber(internalCode);
            responseCode = HttpStatus.NO_CONTENT;
        } catch (BarberDoNotExistsException | InvalidParameterException exception) {
            responseCode = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(responseCode);
    }

    /**
     * REST GET method is responsible to retrieve
     * an existing barber information.
     *
     * @param internalCode the barber internal code to search
     * @return 200 and the barber information if the barber was found;
     * otherwise 404
     */
    @GetMapping(value = "/{internalCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<BarberDTO> getBarber(@PathVariable @NotBlank String internalCode)
        throws InvalidParameterException {
        HttpStatus responseCode = HttpStatus.NOT_FOUND;
        BarberDTO responseBody = null;

        Optional<Barber> barberOptional = this.barberService.getBarber(internalCode);
        if (barberOptional.isPresent()) {
            responseBody = this.barberMapper.fromBarberToBarberDto(barberOptional.get());
            responseCode = HttpStatus.OK;
        }
        return new ResponseEntity<>(responseBody, responseCode);
    }

    /**
     * REST GET method is responsible to retrieve
     * all barbers information.
     *
     * @return 200 and the barbers information if exists
     * at least one barber; otherwise 404
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<BarberDTO>> getBarbers() {
        List<BarberDTO> responseBody = this.barberService.getBarbers()
            .stream()
            .map(this.barberMapper::fromBarberToBarberDto)
            .collect(Collectors.toList());
        HttpStatus responseCode = responseBody.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(responseBody, responseCode);
    }

}
