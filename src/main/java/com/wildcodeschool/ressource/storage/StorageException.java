package com.wildcodeschool.ressource.storage;

/**
 * Exception class for all Exceptions that happens when accessing the filesystem
 */
public class StorageException extends RuntimeException {

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
