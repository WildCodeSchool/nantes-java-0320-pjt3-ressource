package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.FabricPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricPatternRepository extends JpaRepository<FabricPattern, Long> {
}
