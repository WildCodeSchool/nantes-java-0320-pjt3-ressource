/**
 * Interface defining the operations available for interacting with the underlying filesystem
 */
package com.wildcodeschool.ressource.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface StorageService {

    /**
     * Initialization of the filesystem
     * For example : create the expected directories for the storage
     */
    void init();

    /**
     * Store the file in the storage
     * @param file
     */
    void store(MultipartFile file, int indexLocation, String nameFile);

    /**
     * Return all the files in the storage
     * @return
     */
    //Stream<Path> loadAll();

    /**
     * Get the path to the filename in the storage
     * @param filename
     * @return Path to the file
     */
    Path load(String filename, int indexLocation);

    /**
     * Get the file references by filename as a Resource
     * @param filename
     * @return the file
     */
    Resource loadAsResource(String filename, int indexLocation);

    /**
     * Delete everything in the storage
     */
    void deleteByName(String fileName, int indexLocation);

}
