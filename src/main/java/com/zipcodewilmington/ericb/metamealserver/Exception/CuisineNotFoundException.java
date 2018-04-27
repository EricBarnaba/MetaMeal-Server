package com.zipcodewilmington.ericb.metamealserver.Exception;

public class CuisineNotFoundException extends RuntimeException{
    private String message = "Cuisine not found";
    public String getMessage(){
        return this.message;
    }

}
