package com.wildcodeschool.ressource.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String logo;
    private Date creationDate;
    private String specialities;
    private Long employeesNumber;
    private String ceoWords;
    private String serviceLeftWords;
    private String ServiceRightWords;
    private String pictureWords;

    @OneToMany(mappedBy = "company")
    private List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String specialities) {
        this.specialities = specialities;
    }

    public Long getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(Long employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public String getCeoWords() {
        return ceoWords;
    }

    public void setCeoWords(String ceoWords) {
        this.ceoWords = ceoWords;
    }

    public String getServiceLeftWords() {
        return serviceLeftWords;
    }

    public void setServiceLeftWords(String serviceLeftWords) {
        this.serviceLeftWords = serviceLeftWords;
    }

    public String getServiceRightWords() {
        return ServiceRightWords;
    }

    public void setServiceRightWords(String serviceRightWords) {
        ServiceRightWords = serviceRightWords;
    }

    public String getPictureWords() {
        return pictureWords;
    }

    public void setPictureWords(String pictureWords) {
        this.pictureWords = pictureWords;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
