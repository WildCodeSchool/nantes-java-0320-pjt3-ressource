package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Composition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {

    Optional<Composition> findByFiberIdAndProductId(Long fiberId, Long productId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM composition WHERE product_id = :productId",
            nativeQuery = true)
    void deleteByProductId(@Param("productId") Long productId);
}
