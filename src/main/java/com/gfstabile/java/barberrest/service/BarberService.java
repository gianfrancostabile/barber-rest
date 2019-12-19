package com.gfstabile.java.barberrest.service;

import com.gfstabile.java.barberrest.entity.barber.Barber;
import com.gfstabile.java.barberrest.exception.BarberAlreadyExistsException;
import com.gfstabile.java.barberrest.exception.BarberDoNotExistsException;
import com.gfstabile.java.barberrest.exception.InvalidBarberException;
import com.gfstabile.java.barberrest.exception.InvalidParameterException;
import com.gfstabile.java.barberrest.repository.BarberRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The service bean related to Barber entity
 *
 * @author G. F. Stabile
 */
@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    /**
     * Inserts a new barber into the database.
     *
     * @param barber the new barber to insert
     * @throws InvalidBarberException       if the barber instance is invalid
     * @throws BarberAlreadyExistsException if the internal code of
     *                                      the new barber already exists
     */
    public void insertBarber(Barber barber) throws InvalidBarberException, BarberAlreadyExistsException {
        if (Objects.nonNull(barber) && barber.isValid()) {
            try {
                this.barberRepository.save(barber);
            } catch (Exception exception) {
                throw new BarberAlreadyExistsException();
            }
        } else {
            throw new InvalidBarberException();
        }
    }

    /**
     * Updates the barber values from the database.
     * <p>
     * First find a barber with the given internal code.
     * If not exists throws a {@link BarberDoNotExistsException};
     * otherwise removes the barber by his internal code and
     * inserts the barber with the new values.
     * </p>
     *
     * @param barber the barber with new values
     * @throws InvalidBarberException     if the barber instance is invalid
     * @throws BarberDoNotExistsException if the internal code of the
     *                                    barber to update do not exists
     */
    public void updateBarber(Barber barber) throws InvalidBarberException, BarberDoNotExistsException {
        if (Objects.nonNull(barber) && barber.isValid()) {
            try {
                this.barberRepository.updateBarber(barber.getInternalCode(), barber.getName(), barber.getSurname());
            } catch (Exception exception) {
                throw new BarberDoNotExistsException();
            }
        } else {
            throw new InvalidBarberException();
        }
    }

    /**
     * Removes a barber by his internal code.
     *
     * @param internalCode the barber's internal code
     * @throws InvalidParameterException  if the internalCode parameter
     *                                    is invalid
     * @throws BarberDoNotExistsException if the internal code of the
     *                                    barber to update do not exists
     */
    public void deleteBarber(String internalCode) throws InvalidParameterException, BarberDoNotExistsException {
        if (Strings.isNotBlank(internalCode)) {
            if (this.barberRepository.findByInternalCode(internalCode)
                .isPresent()) {
                this.barberRepository.deleteByInternalCode(internalCode);
            } else {
                throw new BarberDoNotExistsException();
            }
        } else {
            throw new InvalidParameterException("internalCode");
        }
    }

    /**
     * Retrieves the barber information by his internal code.
     *
     * @param internalCode the barber's internal code
     * @return an optional with the barber information
     * @throws InvalidParameterException if the internalCode parameter
     *                                   is invalid
     */
    public Optional<Barber> getBarber(String internalCode) throws InvalidParameterException {
        if (Strings.isNotBlank(internalCode)) {
            return this.barberRepository.findByInternalCode(internalCode);
        } else {
            throw new InvalidParameterException("internalCode");
        }
    }

    /**
     * Retrieves a list with all the barbers.
     *
     * @return the list with all the barbers
     */
    public List<Barber> getBarbers() {
        return this.barberRepository.findAll();
    }
}
