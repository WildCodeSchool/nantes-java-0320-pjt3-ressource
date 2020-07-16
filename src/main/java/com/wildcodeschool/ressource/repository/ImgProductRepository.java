package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImgProductRepository extends JpaRepository<ImageProduct, Long> {
}
