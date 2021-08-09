package com.example.restservice.Models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
//mark class as an Entity
@Entity
//defining class name as Table name
@Table
@Getter
@Setter
public class Book
{
    @Id
    @Column
    private int bookid;
    @Column
    private String bookname;
    @Column
    private String author;
    @Column
    private int price;

}