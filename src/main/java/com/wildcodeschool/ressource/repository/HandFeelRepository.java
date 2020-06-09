package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.HandFeel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandFeelRepository extends JpaRepository<HandFeel, Long> {
}
