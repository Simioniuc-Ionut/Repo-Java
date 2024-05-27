package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/authors")
@Api(value = "Author Management System", description = "Endpoints for managing authors")
public class AuthorController {

    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorController(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }
    @ApiOperation(value = "View a list of all authors", response = List.class)
    @GetMapping ("/list")
    public List<Author> getAuthors () {
        return authorRepository.findAll();
    }
}