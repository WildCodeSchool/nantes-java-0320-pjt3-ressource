package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.TechnicalProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalPropertyRepository extends JpaRepository<TechnicalProperty, Long> {
}
