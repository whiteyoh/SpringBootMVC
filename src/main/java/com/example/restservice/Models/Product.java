package com.example.restservice.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private int id;
    private String description;
    private double price;
    private int category;

}
