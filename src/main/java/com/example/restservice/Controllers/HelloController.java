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
public class HelloController
{

    @GetMapping("/hello")
    private String hello()
    {
        return "Hello from Spring boot codeschool";
    }


}
