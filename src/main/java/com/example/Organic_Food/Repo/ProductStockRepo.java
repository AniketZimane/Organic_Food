package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.Product;
import com.example.Organic_Food.Entity.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductStockRepo extends JpaRepository<ProductStock,Integer> {
    @Query("SELECT p.closingQty FROM ProductStock p WHERE p.productId = :productId order by systemDate desc limit 1")
    Integer findClosingStockByProductId(@Param("productId") Integer productId);
    List<ProductStock> findProductStockByProductId(Integer productId);
}
