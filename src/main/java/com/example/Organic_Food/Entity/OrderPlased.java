package com.example.Organic_Food.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class OrderPlased {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Blog_seq")
    @SequenceGenerator(name = "Blog_seq")
    Integer id;
    String custName;
    String buyProductName;
    String mobNO;
    Integer totalAmt;
    Integer totalQty;
    Integer paymentType;
    String homeOfficeYN;
    String homeAddress;
    String officeAddress;
    @CreationTimestamp
    LocalDateTime systemDate;

    public OrderPlased() {
    }

    public OrderPlased(Integer id, String custName, String buyProductName, String mobNO, Integer totalAmt, Integer totalQty, Integer paymentType, String homeOfficeYN, String homeAddress, String officeAddress, LocalDateTime systemDate) {
        this.id = id;
        this.custName = custName;
        this.buyProductName = buyProductName;
        this.mobNO = mobNO;
        this.totalAmt = totalAmt;
        this.totalQty = totalQty;
        this.paymentType = paymentType;
        this.homeOfficeYN = homeOfficeYN;
        this.homeAddress = homeAddress;
        this.officeAddress = officeAddress;
        this.systemDate = systemDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getBuyProductName() {
        return buyProductName;
    }

    public void setBuyProductName(String buyProductName) {
        this.buyProductName = buyProductName;
    }

    public String getMobNO() {
        return mobNO;
    }

    public void setMobNO(String mobNO) {
        this.mobNO = mobNO;
    }

    public Integer getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Integer totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Integer getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Integer totalQty) {
        this.totalQty = totalQty;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public String getHomeOfficeYN() {
        return homeOfficeYN;
    }

    public void setHomeOfficeYN(String homeOfficeYN) {
        this.homeOfficeYN = homeOfficeYN;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public LocalDateTime getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDateTime systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "OrderPlased{" +
                "id=" + id +
                ", custName='" + custName + '\'' +
                ", buyProductName='" + buyProductName + '\'' +
                ", mobNO='" + mobNO + '\'' +
                ", totalAmt=" + totalAmt +
                ", totalQty=" + totalQty +
                ", paymentType=" + paymentType +
                ", homeOfficeYN='" + homeOfficeYN + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", officeAddress='" + officeAddress + '\'' +
                ", systemDate=" + systemDate +
                '}';
    }
}
