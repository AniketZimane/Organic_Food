package com.example.Organic_Food.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;

@Entity
public class Review {
    @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "review_seq")
            @SequenceGenerator(name = "review_seq")
    Integer id;
    String name;
    String Profession;
    String extension;
    String briefInfo;
    @CreationTimestamp
    LocalDate systemDate;

    public Review() {
    }

    public Review(Integer id, String name, String profession, String extension, String briefInfo, LocalDate systemDate) {
        this.id = id;
        this.name = name;
        Profession = profession;
        this.extension = extension;
        this.briefInfo = briefInfo;
        this.systemDate = systemDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public LocalDate getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDate systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Profession='" + Profession + '\'' +
                ", extension='" + extension + '\'' +
                ", briefInfo='" + briefInfo + '\'' +
                ", systemDate=" + systemDate +
                '}';
    }
}
