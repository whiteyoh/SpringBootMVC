package com.example.restservice.Services;
import java.util.ArrayList;
import java.util.List;

import com.example.restservice.Models.Book;
import com.example.restservice.Repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//defining the business logic
@Service
public class BooksService
{
    @Autowired
    BooksRepository booksRepository;

    public List<Book> getAllBooks()
    {
        List<Book> books = new ArrayList<Book>();
        booksRepository.findAll().forEach(book1 -> books.add(book1));
        return books;
    }

    //saving a specific record by using the method save() of CrudRepository




    //getting a specific record by using the method findById() of CrudRepository

    public Book getBooksById(int id)
    {
        return booksRepository.findById(id).get();
    }

    //deleting a specific record by using the method deleteById() of CrudRepository

    public void delete(int id)
    {
        booksRepository.deleteById(id);
    }
    //updating a record

    public void update(Book book, int bookid)
    {
        booksRepository.save(book);
    }

    public void saveOrUpdate(Book book)
    {
        booksRepository.save(book);
    }
}