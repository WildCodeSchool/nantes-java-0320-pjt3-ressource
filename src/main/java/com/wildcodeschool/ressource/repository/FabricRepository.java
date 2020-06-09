package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {
}
