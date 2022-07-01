package com.example.library.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemErrorResponse {

    private int status;
    private String message;
    private Long timeStamp;
    
}
