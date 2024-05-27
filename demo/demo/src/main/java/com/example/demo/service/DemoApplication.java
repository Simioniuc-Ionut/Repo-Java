package com.example.demo.service;

import com.example.demo.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	//public static void main(String[] args) {
//SpringApplication.run(DemoApplication.class, args);
//}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("Hello world!");

		BookClient client = new BookClient();

		// Get books
		client.getBooks();
		//client.updateTitle("New Book", "New Book Title");
		// Add a book
		Book newBook = new Book("New Book", 200, "RO", 2024);
		client.addBook(newBook);

	}
}