package com.example.library.exceptions;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super("No record exist for given id");
    }

    public ItemNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotFoundException(Throwable cause) {
        super(cause);
    }

}
