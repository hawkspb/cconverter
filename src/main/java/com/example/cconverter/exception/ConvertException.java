package com.example.cconverter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="No such Currency")
public class ConvertException extends RuntimeException {

    public ConvertException(String currency) {
        super(String.format("No such currency: %s", currency));
    }
}
