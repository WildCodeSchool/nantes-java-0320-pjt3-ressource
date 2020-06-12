package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Finishing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinishingRepository extends JpaRepository<Finishing, Long> {
}
