package com.br.cleyton.gymprosystem.errors;

public class ApiRequestException extends RuntimeException{ //custon exception that we will throw in our api

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
