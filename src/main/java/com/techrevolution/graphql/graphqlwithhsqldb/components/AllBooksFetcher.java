package com.techrevolution.graphql.graphqlwithhsqldb.components;

import com.techrevolution.graphql.graphqlwithhsqldb.model.Book;
import com.techrevolution.graphql.graphqlwithhsqldb.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AllBooksFetcher implements DataFetcher<List<Book>> {

    private final BookRepository bookRepository;

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return bookRepository.findAll();
    }
}
