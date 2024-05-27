package com.example.demo.controller;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
@Api(value = "Book Management System", description = "Endpoints for managing books")
public class BookController {

    private final BookRepository bookRepository;
    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @ApiOperation(value = "View a list of all books", response = List.class)
    @GetMapping ("/list")
    public List<Book> getBooks () {
        return bookRepository.findAll();
    }

    @ApiOperation(value = "Add a new book")
    @PutMapping("/add")
    public void addBook(){
        Book b = new Book();
        b.setTitle("The Lord of the Rings");
        b.setPage(1000);
        b.setLanguage("Eng");
        b.setYear(2003);
        bookRepository.save(b);
    }
    /*
        @PostMapping("/add")
        public void addBook(@RequestBody Book b){
            bookRepository.save(b);
        }
        si clientul ar trebui sa trimita un json de forma:
            {
            "title": "The Lord of the Rings",
            "page": 1000,
            "language": "Eng",
            "year": 2003
        }
     */
    @ApiOperation(value = "Update the title of a book")
    @PatchMapping("/update")
    public void updateTitle(@RequestParam String title, @RequestParam String newTitle){
        Book b = bookRepository.findByTitle(title);
        b.setTitle(newTitle);
        bookRepository.save(b);
    }

    @ApiOperation(value = "Delete a book")
    @DeleteMapping("/delete")
    public void deleteBook(@RequestParam String title){
        Book b = bookRepository.findByTitle(title);
        bookRepository.delete(b);
    }


}
