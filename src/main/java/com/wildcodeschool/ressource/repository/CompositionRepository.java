package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Composition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CompositionRepository extends JpaRepository<Composition, Long> {

}
