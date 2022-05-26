package com.stockmanager.model.utils.exceptions;

public class DuplicateKeyException extends RuntimeException {
    public DuplicateKeyException() {
        super("Key not unique.");
    }
}
