package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepo extends JpaRepository<Product,Integer> {
   @Query("SELECT p.stock FROM Product p WHERE p.id = :id")
    Integer findActualStockById(@Param("id") Integer id);
    @Query("SELECT p.prize FROM Product p WHERE p.id = :id")
    Integer findProductPrizeById(@Param("id") Integer id);
    @Query("SELECT p.name FROM Product p WHERE p.id = :id")
    String findProductNameById(@Param("id") Integer id);
    @Query("SELECT p.id FROM Product p WHERE p.name = :name")
    Integer findProductIdByName(@Param("name") String name);
    @Query("SELECT p.id FROM Product p WHERE p.name IN :name")
    List<Integer> findProductIdsByNames(@Param("name") List<String> name);
    @Query("SELECT p.id FROM Product p WHERE p.name IN :name")
    Integer findProductIdsByName(@Param("name") String name);
}
