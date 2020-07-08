package com.wildcodeschool.ressource.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "technical_property")
public class TechnicalProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String technicalProperty;

    @ManyToMany(mappedBy = "technicalProperties", cascade = CascadeType.ALL)
    private List<Feature> features = new ArrayList<>();

    public TechnicalProperty() {
    }

    public TechnicalProperty(String technicalProperty, List<Feature> features) {
        this.technicalProperty = technicalProperty;
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTechnicalProperty() {

        return technicalProperty;
    }

    public void setTechnicalProperty(String technicalProperty) {
        this.technicalProperty = technicalProperty;
    }
}
