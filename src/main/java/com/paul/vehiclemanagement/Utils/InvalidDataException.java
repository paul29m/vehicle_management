package com.paul.vehiclemanagement.Utils;

/*
* Custom exception class used in validation of data
*
* */
public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String message) {
        super(message);
    }
}
