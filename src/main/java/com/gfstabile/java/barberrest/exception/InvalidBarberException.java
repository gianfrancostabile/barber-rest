package com.gfstabile.java.barberrest.exception;

/**
 * Exception used if the barber instance null
 * or any field is invalid
 *
 * @author G. F. Stabile
 */
public class InvalidBarberException extends Exception {
    public InvalidBarberException() {
        super("The barber instance is null or any field is invalid");
    }
}
