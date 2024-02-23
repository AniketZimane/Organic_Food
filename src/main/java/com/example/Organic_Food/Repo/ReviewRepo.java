package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReviewRepo extends JpaRepository<Review,Integer> {
}
