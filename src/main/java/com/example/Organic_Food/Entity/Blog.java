package com.example.Organic_Food.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Blog_seq")
    @SequenceGenerator(name = "Blog_seq")
    Integer id;
    String title;
    String briefInfo;
    String extension;
    String user;
    @CreationTimestamp
    LocalDate systemDate;

    public Blog() {
    }

    public Blog(Integer id, String title, String briefInfo, String extension, String user, LocalDate systemDate) {
        this.id = id;
        this.title = title;
        this.briefInfo = briefInfo;
        this.extension = extension;
        this.user = user;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDate systemDate) {
        this.systemDate = systemDate;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", briefInfo='" + briefInfo + '\'' +
                ", extension='" + extension + '\'' +
                ", user='" + user + '\'' +
                ", systemDate=" + systemDate +
                '}';
    }
}
