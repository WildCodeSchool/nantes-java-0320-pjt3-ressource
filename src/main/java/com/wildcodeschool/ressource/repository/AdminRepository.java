package com.wildcodeschool.ressource.repository;

import com.wildcodeschool.ressource.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {

    Optional<Admin> findByUsername(String userName);
    List<Admin> findAllByOrderByIdDesc();

    @Query(value = "SELECT DISTINCT admin.id FROM admin " +
            "JOIN role ON role_id = role.id " +
            "WHERE username LIKE %:search% " +
            "OR email LIKE %:search% " +
            "OR role.role LIKE %:search%" , nativeQuery = true)
    List<Long> findAllSearch(@Param("search") String search);

    List<Admin> findAllByIdIn(List<Long> ids);
}
