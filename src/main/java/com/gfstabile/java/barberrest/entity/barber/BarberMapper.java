package com.gfstabile.java.barberrest.entity.barber;

import org.springframework.stereotype.Component;

/**
 * The mapper's bean between Barber and BarberDTO
 *
 * @author G. F. Stabile
 */
@Component
public class BarberMapper {

    /**
     * Returns a {@link Barber} instance using the provided {@link BarberDTO} instance.
     * <p>
     * If the {@link BarberDTO}'s instance is {@code null} a
     * {@code null} will be returned.
     * </p>
     *
     * @param barberDTO the barber dto instance to map to barber.
     * @return if {@link BarberDTO} instance is {@code null},
     * {@code null} will be returned; otherwise an instance of
     * {@link Barber} will be returned.
     */
    public Barber fromBarberDtoToBarber(BarberDTO barberDTO) {
        return barberDTO != null ?
            new Barber(0L, barberDTO.getInternalCode(), barberDTO.getName(), barberDTO.getSurname()) :
            null;
    }

    /**
     * Returns a {@link BarberDTO} instance using the provided {@link Barber} instance.
     * <p>
     * If the {@link Barber}'s instance is {@code null} a
     * {@code null} will be returned.
     * </p>
     *
     * @param barber the barber instance to map to barber dto.
     * @return if {@link Barber} instance is {@code null},
     * {@code null} will be returned; otherwise an instance of
     * {@link BarberDTO} will be returned.
     */
    public BarberDTO fromBarberToBarberDto(Barber barber) {
        return barber != null ? new BarberDTO(barber.getInternalCode(), barber.getName(), barber.getSurname()) : null;
    }

}
