package com.example.Organic_Food.Repo;

import com.example.Organic_Food.Entity.ProductStock;
import com.example.Organic_Food.Entity.Temp_order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface TempOrderRepo extends JpaRepository<Temp_order,Integer> {
    @Query("SELECT p.productQty FROM Temp_order p WHERE p.userId = :userId")
    Integer findProductQtyByUserId(@Param("userId") String userId);
    @Query("SELECT p.productId FROM Temp_order p WHERE p.userId = :userId")
    Integer findProductIdByUserId(@Param("userId") String userId);
    @Query("SELECT p.productQty FROM Temp_order p WHERE p.userId = :userId and p.productId=:productId")
    Integer findProductQtyByUserId(@Param("userId")String userId,@Param("productId") Integer productId);
    List<Temp_order> findByUserId(String productId);
    @Modifying
    @Query("DELETE FROM Temp_order p WHERE p.userId = :userId AND p.productId = :productId")
    void deleteByUserIdAndProductId(@Param("userId") String userId, @Param("productId") Integer productId);

}
