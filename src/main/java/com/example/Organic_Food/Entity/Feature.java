package com.example.Organic_Food.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "feature_seq")
    @SequenceGenerator(name = "feature_seq")
    Integer id;
    String title;
    String briefInfo;
    String detailsInfo;
    String extension;
    @CreationTimestamp
    LocalDate systemDate;

    public Feature() {
    }

    public Feature(Integer id, String title, String briefInfo, String detailsInfo, String extension, LocalDate systemDate) {
        this.id = id;
        this.title = title;
        this.briefInfo = briefInfo;
        this.detailsInfo = detailsInfo;
        this.extension = extension;
        this.systemDate = systemDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBriefInfo() {
        return briefInfo;
    }

    public void setBriefInfo(String briefInfo) {
        this.briefInfo = briefInfo;
    }

    public String getDetailsInfo() {
        return detailsInfo;
    }

    public void setDetailsInfo(String detailsInfo) {
        this.detailsInfo = detailsInfo;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public LocalDate getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDate systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", briefInfo='" + briefInfo + '\'' +
                ", detailsInfo='" + detailsInfo + '\'' +
                ", extension='" + extension + '\'' +
                ", systemDate=" + systemDate +
                '}';
    }
}
