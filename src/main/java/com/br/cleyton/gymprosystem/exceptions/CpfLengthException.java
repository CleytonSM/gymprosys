package com.br.cleyton.gymprosystem.exceptions;

public class CpfLengthException extends RuntimeException{

    public CpfLengthException() {
    }

    public CpfLengthException(String message) {
        super(message);
    }

    public CpfLengthException(String message, Throwable cause) {
        super(message, cause);
    }

    public CpfLengthException(Throwable cause) {
        super(cause);
    }

    public CpfLengthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
