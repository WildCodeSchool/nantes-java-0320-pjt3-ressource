package com.wildcodeschool.ressource.storage;

/**
 * Exception class that'll be used when attempting to access to a file that can't be found
 */
public class StorageFileNotFoundException extends StorageException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
