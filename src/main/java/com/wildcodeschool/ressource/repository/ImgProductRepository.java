package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgProductRepository extends JpaRepository<ImageProduct, Long> {
}
