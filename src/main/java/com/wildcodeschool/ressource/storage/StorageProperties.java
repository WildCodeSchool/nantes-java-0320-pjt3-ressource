/**
 * Class creating an offering access to a storage.location key in the application.properties file
 * The value of this property will be used as the directory for the file storage
 */
package com.wildcodeschool.ressource.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */

    private String locationCompany = "/upload/company";
    private String locationProducts = "/upload/products";

    public String getLocationCompany() {
        return locationCompany;
    }

    public void setLocationCompany(String locationCompany) {
        this.locationCompany = locationCompany;
    }

    public String getLocationProducts() {
        return locationProducts;
    }

    public void setLocationProducts(String locationProducts) {
        this.locationProducts = locationProducts;
    }
}
