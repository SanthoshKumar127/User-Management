package com.assesment.userManagement.CustomException;

public class DuplicateEmailIdException extends RuntimeException{
    public DuplicateEmailIdException(String message){
        super(message);
    }
}
