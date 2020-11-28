package com.techrevolution.graphql.graphqlwithhsqldb.components;

import com.techrevolution.graphql.graphqlwithhsqldb.model.Book;
import com.techrevolution.graphql.graphqlwithhsqldb.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookByIsbnFetcher implements DataFetcher<Book> {

    private final BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
        String isbn = dataFetchingEnvironment.getArgument("isbn");
        return bookRepository.findById(isbn).get();
    }
}
