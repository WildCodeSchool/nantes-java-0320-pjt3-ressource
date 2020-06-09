package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
