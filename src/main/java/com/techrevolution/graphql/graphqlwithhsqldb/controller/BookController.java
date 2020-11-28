package com.techrevolution.graphql.graphqlwithhsqldb.controller;

import com.techrevolution.graphql.graphqlwithhsqldb.service.BookService;
import graphql.ExecutionResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
@Slf4j
public class BookController {

    private final BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ExecutionResult getBookDetails(@RequestBody String query) {

        log.info("Getting query is :-- {}" , query);

        return bookService.getGraphQL().execute(query);
    }

}
