package com.example.demo;

import com.example.demo.model.Book;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class BookClient {
    private static final String BASE_URL = "http://localhost:8080/books";
    private final RestTemplate restTemplate;

    public BookClient(){
        this.restTemplate = new RestTemplate();
    }

    public void getBooks(){
        String url = BASE_URL + "/list";
        System.out.println("Constructed URL: " + url);

        try {
            Book[] books = restTemplate.getForObject(url, Book[].class);
            assert books != null;
            for(Book book : books) {
                System.out.println(book);
            }
        } catch (HttpClientErrorException e) {
            System.err.println("Request failed: " + e.getStatusCode() + " " + e.getStatusText());
            System.err.println("Response body: " + e.getResponseBodyAsString());
        }
    }


    public void addBook(Book book) {
        String url = BASE_URL + "/add";
        restTemplate.postForObject(url, book, Book.class);
    }

    public void updateTitle(String title, String newTitle) {
        String url = BASE_URL + "/update/title?title={title}&newTitle={newTitle}";
        restTemplate.put(url, null, title, newTitle);
    }


    public void deleteBook(String title){
        String url = BASE_URL + "/delete?title=" + title;
        restTemplate.delete(url);
    }


}
