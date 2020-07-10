package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM feature WHERE product_id = :productId",
            nativeQuery = true)
    void deleteFeatureByProductId(@Param("productId") Long productId);
}
