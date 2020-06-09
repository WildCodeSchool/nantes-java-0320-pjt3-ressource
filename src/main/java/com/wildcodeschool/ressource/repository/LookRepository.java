package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Look;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LookRepository extends JpaRepository<Look, Long> {
}
