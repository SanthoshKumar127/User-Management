package com.assesment.userManagement.CustomException;

public class DuplicateUserNameException extends  RuntimeException{
    public DuplicateUserNameException(String message){
        super(message);
    }
}
