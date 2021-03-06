package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Fiber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiberRepository extends JpaRepository<Fiber, Long> {
}
