package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {
}
