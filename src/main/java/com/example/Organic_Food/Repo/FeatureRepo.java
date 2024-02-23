package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FeatureRepo extends JpaRepository<Feature,Integer> {
}
