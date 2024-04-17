package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface RegistrationRepo extends JpaRepository<Registration,Integer> {
    List<Registration> findByEmail(String email);
    @Query("from Registration where email=:email")
    Registration getByEmail(String email);
    @Query("SELECT p.userType FROM Registration p WHERE p.username = :username")
    String findUserTypeByUsername(@Param("username") String username);
}
