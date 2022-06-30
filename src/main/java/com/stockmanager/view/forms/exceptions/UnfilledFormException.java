package com.stockmanager.view.forms.exceptions;

public class UnfilledFormException extends RuntimeException {
    public UnfilledFormException() {
        super("Todos os campos devem estar preenchidos!");
    }
}
