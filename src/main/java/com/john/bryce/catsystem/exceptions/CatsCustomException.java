package com.john.bryce.catsystem.exceptions;

import org.springframework.stereotype.Repository;

public class CatsCustomException extends Exception{
    public CatsCustomException(String message) {
        super(message);
    }

}
