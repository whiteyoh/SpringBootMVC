package com.example.restservice.Controllers;
import java.util.List;

import com.example.restservice.Models.Book;
import com.example.restservice.Services.BooksService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;


//mark class as Controller
@RestController
public class DataController
{
    private static final Logger logger= LoggerFactory.getLogger(com.example.restservice.Controllers.DataController.class);
    //autowire the BooksService class
    @Autowired
    BooksService booksService;

    //creating a get mapping that retrieves all the books detail from the database

    @GetMapping("/book")
    private List<Book> getAllBooks()
    {
        logger.debug("------ Entered @GetMapping / Book DataController -----");
        return booksService.getAllBooks();
    }
    
    //creating a get mapping that retrieves the detail of a specific book

    @GetMapping("/book/{bookid}")
    private Book getBooks(@PathVariable("bookid") int bookid)
    {

        return booksService.getBooksById(bookid);
    }
    //creating a delete mapping that deletes a specified book

    @DeleteMapping("/book/{bookid}")
    private void deleteBook(@PathVariable("bookid") int bookid)
    {
        booksService.delete(bookid);
    }
    //creating post mapping that post the book detail in the database


    @PostMapping("/book")
    private int saveBook(@RequestBody Book book)
    {
        System.out.println("hello from codeschool");
        System.out.println("pauls book name: " + book.getBookname());
        booksService.saveOrUpdate(book);
        return book.getBookid();
    }
    //creating put mapping that updates the book detail

    @PutMapping("/books")
    private Book update(@RequestBody Book book)
    {
        booksService.saveOrUpdate(book);
        return book;
    }
}
