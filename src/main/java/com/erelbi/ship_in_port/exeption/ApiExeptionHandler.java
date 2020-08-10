package com.erelbi.ship_in_port.exeption;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExeptionHandler {

    private ZoneId zoneId = ZoneId.of("Europe/Istanbul");
    
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleApiRequestException(Exception e, WebRequest webRequest) {
        
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(zoneId)
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler({EntityNotFoundExeption.class})
    public ResponseEntity<Object> notFoundException(Exception e) {
        
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(zoneId)
        );
        return new ResponseEntity<>(apiException, notFound);
    }
    
}