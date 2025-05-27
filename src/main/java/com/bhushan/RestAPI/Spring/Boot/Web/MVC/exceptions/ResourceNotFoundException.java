package com.bhushan.RestAPI.Spring.Boot.Web.MVC.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
