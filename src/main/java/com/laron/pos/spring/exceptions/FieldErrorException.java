package com.laron.pos.spring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorException  extends RuntimeException{

    private String location;
    private String fieldMessage;


    public FieldErrorException(String message, String location, String fieldMessage){

        super(message);

        this.fieldMessage= fieldMessage;
        this.location = location;
    }
}
