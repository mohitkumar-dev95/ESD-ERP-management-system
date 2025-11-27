package com.mohit.esd_mini.helper;

// Custom exception class for ResourceNotFoundException
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message); // Pass the message to the superclass (RuntimeException)
    }
}
