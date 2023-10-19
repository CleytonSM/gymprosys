package com.br.cleyton.gymprosystem.exceptions.builder;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ApiException {
    // class that represent the actual exception

    private final String message;
    private final HttpStatus status;

    private final ZonedDateTime timestamp;

    public ApiException(String message, HttpStatus status, ZonedDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
}
