package com.example.Organic_Food.Entity;

import jakarta.persistence.*;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "Reg_seq")
    @SequenceGenerator(name = "Reg_seq")
    Integer id;
    String username;
    String password;
    String email;
    String tCode;
    String userType;
    String farmerId;

    public Registration() {
    }

    public Registration(Integer id, String username, String password, String email, String tCode, String userType, String farmerId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tCode = tCode;
        this.userType = userType;
        this.farmerId = farmerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", tCode='" + tCode + '\'' +
                ", userType='" + userType + '\'' +
                ", farmerId='" + farmerId + '\'' +
                '}';
    }
}
