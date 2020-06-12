package com.wildcodeschool.ressource.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hand_feel")
public class HandFeel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String handFeel;

    @OneToMany(mappedBy = "handFeel", cascade = CascadeType.ALL)
    private List<Feature> features = new ArrayList<>();

    public HandFeel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHandFeel() {
        return handFeel;
    }

    public void setHandFeel(String handFeel) {
        this.handFeel = handFeel;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
