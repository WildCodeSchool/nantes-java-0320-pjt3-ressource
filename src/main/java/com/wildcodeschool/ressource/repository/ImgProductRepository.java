package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Company;
import com.wildcodeschool.ressource.entity.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ImgProductRepository extends JpaRepository<ImageProduct, Long> {


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM image_product WHERE product_id = :productId",
            nativeQuery = true)
    void deleteByProductId(@Param("productId") Long productId);
}
