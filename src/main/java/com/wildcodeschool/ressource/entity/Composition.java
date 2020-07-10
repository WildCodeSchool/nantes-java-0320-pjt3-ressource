package com.wildcodeschool.ressource.entity;

import javax.persistence.*;

@Entity
@Table(name = "composition")
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fiber_id")
    private Fiber fiber;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Double pourcentage;

    public Composition() {

    }

    public Composition(Fiber fiber, Double pourcentage, Product product) {
        this.fiber = fiber;
        this.pourcentage = pourcentage;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fiber getFiber() {
        return fiber;
    }

    public void setFiber(Fiber fiber) {
        this.fiber = fiber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }
}