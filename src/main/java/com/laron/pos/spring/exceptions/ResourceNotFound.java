package com.laron.pos.spring.exceptions;


public class ResourceNotFound  extends RuntimeException{

    public ResourceNotFound(String message){

        super(message);
    }
}
