package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {
}
