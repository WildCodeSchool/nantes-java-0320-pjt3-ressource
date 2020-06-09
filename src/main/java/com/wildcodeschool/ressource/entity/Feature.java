package com.wildcodeschool.ressource.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "feature")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "finishing_id")
    private Finishing finishing;

    @ManyToOne
    @JoinColumn(name = "handFeel_id")
    private HandFeel handFeel;

    @ManyToOne
    @JoinColumn(name = "look_id")
    private Look look;

    @ManyToOne
    @JoinColumn(name = "fabric_id")
    private Fabric fabric;

    @ManyToMany
    @JoinTable(name = "feature_technical_property",
            joinColumns = @JoinColumn (name = "feature_id"),
            inverseJoinColumns = @JoinColumn (name = "technical_property_id"))
    private List<TechnicalProperty> technicalProperties = new ArrayList<>();

    @OneToMany(mappedBy = "feature")
    private List<Product> products = new ArrayList<>();

    public Feature() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Finishing getFinishing() {
        return finishing;
    }

    public void setFinishing(Finishing finishing) {
        this.finishing = finishing;
    }

    public HandFeel getHandFeel() {
        return handFeel;
    }

    public void setHandFeel(HandFeel handFeel) {
        this.handFeel = handFeel;
    }

    public Look getLook() {
        return look;
    }

    public void setLook(Look look) {
        this.look = look;
    }

    public Fabric getFabric() {
        return fabric;
    }

    public void setFabric(Fabric fabric) {
        this.fabric = fabric;
    }

    public List<TechnicalProperty> getTechnicalProperties() {
        return technicalProperties;
    }

    public void setTechnicalProperties(List<TechnicalProperty> technicalProperties) {
        this.technicalProperties = technicalProperties;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}