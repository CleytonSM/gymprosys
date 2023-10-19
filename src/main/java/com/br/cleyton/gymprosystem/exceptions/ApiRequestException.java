package com.br.cleyton.gymprosystem.exceptions;

public class ApiRequestException extends RuntimeException{ //custom exception that we will throw in our api

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
