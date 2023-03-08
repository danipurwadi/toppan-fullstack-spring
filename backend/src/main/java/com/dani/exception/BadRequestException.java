package com.dani.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("invalid parameter");
    }
}
