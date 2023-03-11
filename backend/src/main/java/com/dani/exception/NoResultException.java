package com.dani.exception;

public class NoResultException extends RuntimeException {
    public NoResultException() {
        super("no results");
    }
}
