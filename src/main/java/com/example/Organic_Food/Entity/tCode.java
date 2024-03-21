package com.example.Organic_Food.Entity;

import jakarta.persistence.*;

@Entity
public class tCode
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "tCode_seq")
    @SequenceGenerator(name = "tCode_seq")
    Integer id;
    String tCodeValue;

    public tCode() {
    }

    public tCode(Integer id, String tCodeValue) {
        this.id = id;
        this.tCodeValue = tCodeValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettCodeValue() {
        return tCodeValue;
    }

    public void settCodeValue(String tCodeValue) {
        this.tCodeValue = tCodeValue;
    }

    @Override
    public String toString() {
        return "tCode{" +
                "id=" + id +
                ", tCodeValue='" + tCodeValue + '\'' +
                '}';
    }
}
