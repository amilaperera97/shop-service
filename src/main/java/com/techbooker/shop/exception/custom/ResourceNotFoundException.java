package com.techbooker.shop.exception.custom;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(HttpStatus statusCode, String error) {
        super(statusCode,error);
    }

}