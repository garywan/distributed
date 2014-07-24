package com.parallel.exception;

public class CloudException extends Exception {

    private static final long serialVersionUID = 1L;

    public CloudException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public CloudException(String arg0) {
        super(arg0);
    }
    
}
