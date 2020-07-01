package com.wildcodeschool.ressource.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String reference;

    @Column(name = "design_number")
    private Long designNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer width;

    private Integer weight;

    @Column(name = "piece_length")
    private Integer pieceLength;

    private Integer collectionMOQ;

    private Integer productionMOQ;

    @Column(name = "collection_leadtime")
    private Integer collectionLeadtime;

    @Column(name = "production_leadtime")
    private Integer productionLeadtime;

    @Column(name = "washing_comments", columnDefinition = "TEXT")
    private String washingComments;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;

    @OneToMany(mappedBy = "product")
    private List<ImageProduct> imageProducts;

    @ManyToMany
    @JoinTable(name = "product_certification",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "certification_id"))
    private List<Certification> certifications = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "product_care_label",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "care_label_id"))
    private List<CareLabel> careLabels = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "origin_id")
    private Origin origin;

    @ManyToOne
    @JoinColumn(name = "price_id")
    private Price price;

    @ManyToOne
    @JoinColumn(name = "fabric_pattern_id")
    private FabricPattern fabricPattern;

    @OneToMany(mappedBy = "product")
    private List<Composition> compositions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(name = "proposition_list",
            joinColumns = @JoinColumn(name = "product_id_origin"),
            inverseJoinColumns = @JoinColumn(name = "product_id_linked"))
    private List<Product> products = new ArrayList<>();

    public Product() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDesignNumber() {
        return designNumber;
    }

    public void setDesignNumber(Long designNumber) {
        this.designNumber = designNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getPieceLength() {
        return pieceLength;
    }

    public void setPieceLength(Integer pieceLength) {
        this.pieceLength = pieceLength;
    }

    public Integer getCollectionMOQ() {
        return collectionMOQ;
    }

    public void setCollectionMOQ(Integer collectionMOQ) {
        this.collectionMOQ = collectionMOQ;
    }

    public Integer getProductionMOQ() {
        return productionMOQ;
    }

    public void setProductionMOQ(Integer productionMOQ) {
        this.productionMOQ = productionMOQ;
    }

    public Integer getCollectionLeadtime() {
        return collectionLeadtime;
    }

    public void setCollectionLeadtime(Integer collectionLeadtime) {
        this.collectionLeadtime = collectionLeadtime;
    }

    public Integer getProductionLeadtime() {
        return productionLeadtime;
    }

    public void setProductionLeadtime(Integer productionLeadtime) {
        this.productionLeadtime = productionLeadtime;
    }

    public String getWashingComments() {
        return washingComments;
    }

    public void setWashingComments(String washingComments) {
        this.washingComments = washingComments;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public List<ImageProduct> getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(List<ImageProduct> imageProducts) {
        this.imageProducts = imageProducts;
    }

    public List<Certification> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<Certification> certifications) {
        this.certifications = certifications;
    }

    public List<CareLabel> getCareLabels() {
        return careLabels;
    }

    public void setCareLabels(List<CareLabel> careLabels) {
        this.careLabels = careLabels;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public FabricPattern getFabricPattern() {
        return fabricPattern;
    }

    public void setFabricPattern(FabricPattern fabricPattern) {
        this.fabricPattern = fabricPattern;
    }

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
