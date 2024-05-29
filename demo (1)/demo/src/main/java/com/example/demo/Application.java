package com.example.demo;

import com.example.demo.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		// Get books
		BookClient client = new BookClient();

		//client.getBooks();
		//Book newBook = new Book("New Book", 200, "RO", 2024);
		//client.addBook(newBook);
		//client.updateTitle("New Book", "New Book Title");
		// Add a book

		//client.deleteBook("New Book Title");
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello world!";
	}
}
