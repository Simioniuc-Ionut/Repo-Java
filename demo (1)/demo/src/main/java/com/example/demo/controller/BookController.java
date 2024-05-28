package com.example.demo.controller;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
//@Api(value = "Book Management System", description = "Endpoints for managing books")
public class BookController {

    private final BookRepository bookRepository;
    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //@ApiOperation(value = "View a list of all books", response = List.class)
    @GetMapping ("/list")
    public ResponseEntity<List<Book>> getBooks () {
        try {
            List<Book> books = bookRepository.findAll();
            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            // Print the books to the console
            for (Book book : books) {
                System.out.println(book);
            }

            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
  //  @ApiOperation(value = "Add a new book")
  @PostMapping("/add")
  public void addBook(@RequestBody Book book) {
      bookRepository.save(book);
  }

    /*
        si clientul ar trebui sa trimita un json de forma:
            {
            "title": "The Lord of the Rings",
            "page": 1000,
            "language": "Eng",
            "year": 2003
        }
     */
  //  @ApiOperation(value = "Update the title of a book")
    @PutMapping("/update/title")
    public void updateTitle(@RequestParam String title, @RequestParam String newTitle){
        Book b = bookRepository.findByTitle(title);
        b.setTitle(newTitle);
        bookRepository.save(b);
    }

 //   @ApiOperation(value = "Delete a book")
 @DeleteMapping("/delete")
 public ResponseEntity<String> deleteBook(@RequestParam String title){
     Book b = bookRepository.findByTitle(title);
     if (b != null) {
         bookRepository.delete(b);
         return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
     } else {
         return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
     }
 }
//

}
