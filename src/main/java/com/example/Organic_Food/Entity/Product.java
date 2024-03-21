package com.example.Organic_Food.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Product_seq")
    @SequenceGenerator(name = "Product_seq")
    Integer id;
    String category;
    String name;
    String title;
    String mnf_date;
    String exp_date;
    String unit;
    Integer prize;
    String discount_YN;
    Integer discount;
    Integer stock;
    String extension;
    String brief_info;
    String prod_name;
    String prod_add;
    String prod_mob_number;
    @CreationTimestamp
    LocalDateTime systemDate;

    public Product() {
    }

    public Product(Integer id, Integer stock) {
        this.id = id;
        this.stock = stock;
    }

    public Product(Integer id, String category, String name, String title, String mnf_date, String exp_date, String unit, Integer prize, String discount_YN, Integer discount, Integer stock, String extension, String brief_info, String prod_name, String prod_add, String prod_mob_number, LocalDateTime systemDate) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.title = title;
        this.mnf_date = mnf_date;
        this.exp_date = exp_date;
        this.unit = unit;
        this.prize = prize;
        this.discount_YN = discount_YN;
        this.discount = discount;
        this.stock = stock;
        this.extension = extension;
        this.brief_info = brief_info;
        this.prod_name = prod_name;
        this.prod_add = prod_add;
        this.prod_mob_number = prod_mob_number;
        this.systemDate = systemDate;
    }

    public Product(String category, String name, String title, String mnf_date, String exp_date, String unit, Integer prize, String discount_YN, Integer discount, Integer stock, String extension, String brief_info, String prod_name, String prod_add, String prod_mob_number, LocalDateTime systemDate) {
        this.category = category;
        this.name = name;
        this.title = title;
        this.mnf_date = mnf_date;
        this.exp_date = exp_date;
        this.unit = unit;
        this.prize = prize;
        this.discount_YN = discount_YN;
        this.discount = discount;
        this.stock = stock;
        this.extension = extension;
        this.brief_info = brief_info;
        this.prod_name = prod_name;
        this.prod_add = prod_add;
        this.prod_mob_number = prod_mob_number;
        this.systemDate = systemDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMnf_date() {
        return mnf_date;
    }

    public void setMnf_date(String mnf_date) {
        this.mnf_date = mnf_date;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getPrize() {
        return prize;
    }

    public void setPrize(Integer prize) {
        this.prize = prize;
    }

    public String getDiscount_YN() {
        return discount_YN;
    }

    public void setDiscount_YN(String discount_YN) {
        this.discount_YN = discount_YN;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getBrief_info() {
        return brief_info;
    }

    public void setBrief_info(String brief_info) {
        this.brief_info = brief_info;
    }

    public String getProd_name() {
        return prod_name;
    }

    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }

    public String getProd_add() {
        return prod_add;
    }

    public void setProd_add(String prod_add) {
        this.prod_add = prod_add;
    }

    public String getProd_mob_number() {
        return prod_mob_number;
    }

    public void setProd_mob_number(String prod_mob_number) {
        this.prod_mob_number = prod_mob_number;
    }

    public LocalDateTime getSystemDateTime() {
        return systemDate;
    }

    public void setSystemDate(LocalDateTime systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", mnf_date='" + mnf_date + '\'' +
                ", exp_date='" + exp_date + '\'' +
                ", unit='" + unit + '\'' +
                ", prize=" + prize +
                ", discount_YN='" + discount_YN + '\'' +
                ", discount=" + discount +
                ", stock=" + stock +
                ", extension='" + extension + '\'' +
                ", brief_info='" + brief_info + '\'' +
                ", prod_name='" + prod_name + '\'' +
                ", prod_add='" + prod_add + '\'' +
                ", prod_mob_number='" + prod_mob_number + '\'' +
                ", systemDate=" + systemDate +
                '}';
    }
}
