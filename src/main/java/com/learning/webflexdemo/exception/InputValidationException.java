package com.learning.webflexdemo.exception;

public class InputValidationException extends RuntimeException{

    private static final String MSG = "allowed rage is 10 -20";
    private  static final int errorCode = 100;
    private final int input;

    public InputValidationException(int input){
        super(MSG);
        this.input = input;
    }

    public InputValidationException(int input, String message){
        super(message);
        this.input = input;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public int getInput(){
        return input;
    }

}
