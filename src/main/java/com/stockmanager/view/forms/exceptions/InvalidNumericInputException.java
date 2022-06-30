package com.stockmanager.view.forms.exceptions;

public class InvalidNumericInputException extends RuntimeException {

    public InvalidNumericInputException() {
        super("Introdução de número inválido.");
    }
}
