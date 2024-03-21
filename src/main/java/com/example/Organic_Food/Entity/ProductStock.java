package com.example.Organic_Food.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class ProductStock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ProductProduct_seq")
    @SequenceGenerator(name = "ProductStock_seq")
    Integer id;
    Integer productId;
    Integer openingBal;
    Integer returnQty;
    Integer issueQty;
    Integer closingQty;
    @CreationTimestamp
    LocalDateTime systemDate;

    public ProductStock() {
    }

    public ProductStock(Integer productId, Integer openingBal, Integer returnQty, Integer issueQty, Integer closingQty, LocalDateTime systemDate) {
        this.productId = productId;
        this.openingBal = openingBal;
        this.returnQty = returnQty;
        this.issueQty = issueQty;
        this.closingQty = closingQty;
        this.systemDate = systemDate;
    }

    public ProductStock(Integer id, Integer productId, Integer openingBal, Integer returnQty, Integer issueQty, Integer closingQty, LocalDateTime systemDate) {
        this.id = id;
        this.productId = productId;
        this.openingBal = openingBal;
        this.returnQty = returnQty;
        this.issueQty = issueQty;
        this.closingQty = closingQty;
        this.systemDate = systemDate;
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

    public Integer getOpeningBal() {
        return openingBal;
    }

    public void setOpeningBal(Integer openingBal) {
        this.openingBal = openingBal;
    }

    public Integer getReturnQty() {
        return returnQty;
    }

    public void setReturnQty(Integer returnQty) {
        this.returnQty = returnQty;
    }

    public Integer getIssueQty() {
        return issueQty;
    }

    public void setIssueQty(Integer issueQty) {
        this.issueQty = issueQty;
    }

    public Integer getClosingQty() {
        return closingQty;
    }

    public void setClosingQty(Integer closingQty) {
        this.closingQty = closingQty;
    }

    public LocalDateTime getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDateTime systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "ProductStock{" +
                "id=" + id +
                ", productId=" + productId +
                ", openingBal=" + openingBal +
                ", returnQty=" + returnQty +
                ", issueQty=" + issueQty +
                ", closingQty=" + closingQty +
                ", systemDate=" + systemDate +
                '}';
    }
}
