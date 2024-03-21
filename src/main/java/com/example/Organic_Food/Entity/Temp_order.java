package com.example.Organic_Food.Entity;

import jakarta.persistence.*;

@Entity
public class Temp_order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tempOrder_seq")
    @SequenceGenerator(name = "tempOrder_seq")
    Integer id;
    Integer productId;
    Integer productQty;
    String userId;

    public Temp_order() {
    }

    public Temp_order(Integer productId, Integer productQty, String userId) {
        this.productId = productId;
        this.productQty = productQty;
        this.userId = userId;
    }

    public Temp_order(Integer id, Integer productId, Integer productQty, String userId) {
        this.id = id;
        this.productId = productId;
        this.productQty = productQty;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "temp_order{" +
                "id=" + id +
                ", productId=" + productId +
                ", productQty=" + productQty +
                ", userId=" + userId +
                '}';
    }
}
