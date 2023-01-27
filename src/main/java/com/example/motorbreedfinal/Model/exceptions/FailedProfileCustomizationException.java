package com.example.motorbreedfinal.Model.exceptions;

public class FailedProfileCustomizationException extends Exception{

    public FailedProfileCustomizationException(String message){
        super(message);
    }

    public FailedProfileCustomizationException(String message, Throwable cause){
        super(message + " " + cause);
    }
}
