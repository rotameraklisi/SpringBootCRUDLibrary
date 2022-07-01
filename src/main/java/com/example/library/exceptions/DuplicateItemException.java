package com.example.library.exceptions;

public class DuplicateItemException extends Exception {
    public DuplicateItemException() {
        super("Duplicate record");
    }

    public DuplicateItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItemException(Throwable cause) {
        super(cause);
    }

}