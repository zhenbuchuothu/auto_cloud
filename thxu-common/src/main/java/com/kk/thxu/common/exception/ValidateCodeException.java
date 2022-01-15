package com.kk.thxu.common.exception;


public class ValidateCodeException extends Exception{


    private static final long serialVersionUID = -165909102938538024L;

    public ValidateCodeException(String message){
        super(message);
    }
}