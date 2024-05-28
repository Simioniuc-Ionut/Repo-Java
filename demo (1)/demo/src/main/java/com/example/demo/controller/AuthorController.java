package com.example.demo.controller;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import modelcom.example.demo.model.Author;
import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
//@Api(value = "Author Management System", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
//    @ApiOperation(value = "View a list of all authors", response = List.class)
@GetMapping (path = "/list")
public ResponseEntity<List<Author>> getAuthors () {
    try {
        List<Author> authors = authorRepository.findAll();
        return new ResponseEntity<>(authors, HttpStatus.OK);
    } catch (Exception e) {
        // Log the exception
        System.out.println(e.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}