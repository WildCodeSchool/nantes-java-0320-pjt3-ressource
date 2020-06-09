package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.CareLabel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CareLabelRepository extends JpaRepository<CareLabel, Long> {
}
