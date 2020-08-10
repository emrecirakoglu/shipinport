package com.erelbi.ship_in_port.exeption;

public class EntityNotFoundExeption extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EntityNotFoundExeption(String message) {
        super(message);
    }

}