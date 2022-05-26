package com.stockmanager.model.utils.exceptions;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException() {
        super("Value not unique.");
    }
}
