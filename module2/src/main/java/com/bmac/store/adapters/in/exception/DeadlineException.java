package com.bmac.store.adapters.in.exception;

public class DeadlineException extends RuntimeException{
    public DeadlineException() {
        super("Request too late");
    }
}
