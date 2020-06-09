package com.wildcodeschool.ressource.entity;

import org.graalvm.compiler.virtual.phases.ea.EffectList;
import sun.util.resources.Bundles;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String price;

    @OneToMany( mappedBy = "price") //TODO product
    private List<Product> products = new ArrayList<>();

    public Price(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
