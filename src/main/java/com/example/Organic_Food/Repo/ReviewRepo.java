package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.Registration;
import com.example.Organic_Food.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review,Integer> {
    List<Review> findByStatus(String status);
}
