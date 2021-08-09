package com.example.restservice.Repository;
import com.example.restservice.Models.Book;
import org.springframework.data.repository.CrudRepository;

//repository that extends CrudRepository
public interface BooksRepository extends CrudRepository<Book, Integer>
{
}
