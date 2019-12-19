package com.gfstabile.java.barberrest.exception;

/**
 * Exception used if a parameter is invalid
 *
 * @author G. F. Stabile
 */
public class InvalidParameterException extends Exception {
    public InvalidParameterException(String parameterName) {
        super(new StringBuffer("The ").append(parameterName)
            .append("is invalid")
            .toString());
    }
}
