package com.evan.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Long fieldValueUserId;
    private String fieldValueUserEmail;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValueUserId) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValueUserId));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueUserId = fieldValueUserId;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueUserEmail) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValueUserEmail));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueUserEmail = fieldValueUserEmail;
    }
}
