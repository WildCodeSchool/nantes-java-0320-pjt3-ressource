package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.CareLabel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareLabelRepository extends JpaRepository<CareLabel, Long> {
}
