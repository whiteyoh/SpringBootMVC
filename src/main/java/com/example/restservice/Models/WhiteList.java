package com.example.restservice.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Component
@Entity
@Table
public class WhiteList {
    @Id
    @Column
    private int id;
    @Column
    private String userName;

    public int getId(){return id;}
    public String getUserName()
    {
        return userName;
    }
}
