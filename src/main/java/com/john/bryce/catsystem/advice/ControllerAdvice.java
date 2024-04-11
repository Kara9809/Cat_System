package com.john.bryce.catsystem.advice;

import com.john.bryce.catsystem.exceptions.CatsCustomException;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = CatsCustomException.class)
    public ProblemDetail handleCatSystemException(CatsCustomException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    public ProblemDetail handleOtherException(Exception e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"Something went wrong, please try again later");
    }
}
