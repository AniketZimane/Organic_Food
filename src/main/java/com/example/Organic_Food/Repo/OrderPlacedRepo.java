package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.OrderPlased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderPlacedRepo extends JpaRepository<OrderPlased,Integer> {
    @Query("SELECT p.id FROM OrderPlased p WHERE p.custName = :custName order by systemDate desc limit 1")
    String findInvoiceIdByName(@Param("custName") String custName);
    @Query("SELECT p.buyProductName FROM OrderPlased p WHERE p.custName = :custName order by systemDate desc limit 1")
    String findProductsNamesByUserName(@Param("custName") String custName);
}
