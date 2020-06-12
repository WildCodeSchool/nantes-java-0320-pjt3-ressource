package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Fabric;
import com.wildcodeschool.ressource.entity.Origin;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public interface OriginRepository extends JpaRepository<Origin, Long> {

}

