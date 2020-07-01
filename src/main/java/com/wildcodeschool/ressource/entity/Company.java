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
    private String pictureFromSky;
    private String companyMap;
    private String address;

    private String creationDate;
    private String knowhow;
    private String employeesNumber;
    private String industrialPark;
    private String averageProduction;
    private String certifications;

    private String ceoPhoto;
    private String ceoTitle;
    private String ceoWords;
    private String ceoName;

    private String thumbnailOneTopLeft;
    private String thumbnailOneTopMiddle;
    private String thumbnailOneTopRight;
    private String thumbnailOneSideWords;
    private String serviceTopTitle;
    private String serviceTopWords;
    private String serviceTopName;
    private String serviceTopFunction;

    private String thumbnailTwoTopLeft;
    private String thumbnailTwoTopMiddle;
    private String thumbnailTwoTopRight;
    private String thumbnailTwoSideWords;
    private String serviceBottomTitle;
    private String serviceBottomWords;
    private String serviceBottomName;
    private String serviceBottomFunction;

    private String endPhoto;
    private String endWords;

    @OneToMany(mappedBy = "company")
    private List<Product> products = new ArrayList<>();

    public Company() {
    }

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

    public String getPictureFromSky() {
        return pictureFromSky;
    }

    public void setPictureFromSky(String pictureFromSky) {
        this.pictureFromSky = pictureFromSky;
    }

    public String getCompanyMap() {
        return companyMap;
    }

    public void setCompanyMap(String companyMap) {
        this.companyMap = companyMap;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getKnowhow() {
        return knowhow;
    }

    public void setKnowhow(String knowhow) {
        this.knowhow = knowhow;
    }

    public String getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(String employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public String getIndustrialPark() {
        return industrialPark;
    }

    public void setIndustrialPark(String industrialPark) {
        this.industrialPark = industrialPark;
    }

    public String getAverageProduction() {
        return averageProduction;
    }

    public void setAverageProduction(String averageProduction) {
        this.averageProduction = averageProduction;
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    public String getCeoPhoto() {
        return ceoPhoto;
    }

    public void setCeoPhoto(String ceoPhoto) {
        this.ceoPhoto = ceoPhoto;
    }

    public String getCeoTitle() {
        return ceoTitle;
    }

    public void setCeoTitle(String ceoTitle) {
        this.ceoTitle = ceoTitle;
    }

    public String getCeoWords() {
        return ceoWords;
    }

    public void setCeoWords(String ceoWords) {
        this.ceoWords = ceoWords;
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName;
    }

    public String getThumbnailOneTopLeft() {
        return thumbnailOneTopLeft;
    }

    public void setThumbnailOneTopLeft(String thumbnailOneTopLeft) {
        this.thumbnailOneTopLeft = thumbnailOneTopLeft;
    }

    public String getThumbnailOneTopMiddle() {
        return thumbnailOneTopMiddle;
    }

    public void setThumbnailOneTopMiddle(String thumbnailOneTopMiddle) {
        this.thumbnailOneTopMiddle = thumbnailOneTopMiddle;
    }

    public String getThumbnailOneTopRight() {
        return thumbnailOneTopRight;
    }

    public void setThumbnailOneTopRight(String thumbnailOneTopRight) {
        this.thumbnailOneTopRight = thumbnailOneTopRight;
    }

    public String getThumbnailOneSideWords() {
        return thumbnailOneSideWords;
    }

    public void setThumbnailOneSideWords(String thumbnailOneSideWords) {
        this.thumbnailOneSideWords = thumbnailOneSideWords;
    }

    public String getServiceTopTitle() {
        return serviceTopTitle;
    }

    public void setServiceTopTitle(String serviceTopTitle) {
        this.serviceTopTitle = serviceTopTitle;
    }

    public String getServiceTopWords() {
        return serviceTopWords;
    }

    public void setServiceTopWords(String serviceTopWords) {
        this.serviceTopWords = serviceTopWords;
    }

    public String getServiceTopName() {
        return serviceTopName;
    }

    public void setServiceTopName(String serviceTopName) {
        this.serviceTopName = serviceTopName;
    }

    public String getServiceTopFunction() {
        return serviceTopFunction;
    }

    public void setServiceTopFunction(String serviceTopFunction) {
        this.serviceTopFunction = serviceTopFunction;
    }

    public String getThumbnailTwoTopLeft() {
        return thumbnailTwoTopLeft;
    }

    public void setThumbnailTwoTopLeft(String thumbnailTwoTopLeft) {
        this.thumbnailTwoTopLeft = thumbnailTwoTopLeft;
    }

    public String getThumbnailTwoTopMiddle() {
        return thumbnailTwoTopMiddle;
    }

    public void setThumbnailTwoTopMiddle(String thumbnailTwoTopMiddle) {
        this.thumbnailTwoTopMiddle = thumbnailTwoTopMiddle;
    }

    public String getThumbnailTwoTopRight() {
        return thumbnailTwoTopRight;
    }

    public void setThumbnailTwoTopRight(String thumbnailTwoTopRight) {
        this.thumbnailTwoTopRight = thumbnailTwoTopRight;
    }

    public String getThumbnailTwoSideWords() {
        return thumbnailTwoSideWords;
    }

    public void setThumbnailTwoSideWords(String thumbnailTwoSideWords) {
        this.thumbnailTwoSideWords = thumbnailTwoSideWords;
    }

    public String getServiceBottomTitle() {
        return serviceBottomTitle;
    }

    public void setServiceBottomTitle(String serviceBottomTitle) {
        this.serviceBottomTitle = serviceBottomTitle;
    }

    public String getServiceBottomWords() {
        return serviceBottomWords;
    }

    public void setServiceBottomWords(String serviceBottomWords) {
        this.serviceBottomWords = serviceBottomWords;
    }

    public String getServiceBottomName() {
        return serviceBottomName;
    }

    public void setServiceBottomName(String serviceBottomName) {
        this.serviceBottomName = serviceBottomName;
    }

    public String getServiceBottomFunction() {
        return serviceBottomFunction;
    }

    public void setServiceBottomFunction(String serviceBottomFunction) {
        this.serviceBottomFunction = serviceBottomFunction;
    }

    public String getEndPhoto() {
        return endPhoto;
    }

    public void setEndPhoto(String endPhoto) {
        this.endPhoto = endPhoto;
    }

    public String getEndWords() {
        return endWords;
    }

    public void setEndWords(String endWords) {
        this.endWords = endWords;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
