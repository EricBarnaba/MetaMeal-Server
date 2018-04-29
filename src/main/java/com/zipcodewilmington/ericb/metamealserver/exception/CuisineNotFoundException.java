package com.zipcodewilmington.ericb.metamealserver.exception;

public class CuisineNotFoundException extends RuntimeException{
    private String message = "Cuisine not found";

    public CuisineNotFoundException(){}

    public String getMessage(){
        return this.message;
    }

}
