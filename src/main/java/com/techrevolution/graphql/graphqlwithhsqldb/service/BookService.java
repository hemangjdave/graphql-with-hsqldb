package com.techrevolution.graphql.graphqlwithhsqldb.service;

import com.techrevolution.graphql.graphqlwithhsqldb.components.AllBooksFetcher;
import com.techrevolution.graphql.graphqlwithhsqldb.components.BookByIsbnFetcher;
import com.techrevolution.graphql.graphqlwithhsqldb.model.Book;
import com.techrevolution.graphql.graphqlwithhsqldb.repository.BookRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class BookService {

    private Resource resource;

    private GraphQL graphQL;

    private final BookRepository bookRepository;
    private final AllBooksFetcher allBooksFetcher;
    private final BookByIsbnFetcher byIsbnFetcher;

    public BookService(BookRepository bookRepository,
                       AllBooksFetcher allBooksFetcher,
                       BookByIsbnFetcher byIsbnFetcher) {
        this.bookRepository = bookRepository;
        this.allBooksFetcher = allBooksFetcher;
        this.byIsbnFetcher = byIsbnFetcher;
        resource = new ClassPathResource("book/book.graphql");
    }

    @PostConstruct
    private void construct() throws IOException {
        loadBookEntries();
        File file = resource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(file);
        RuntimeWiring runtimeWiring = getRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }

    private void loadBookEntries() {
        Stream.of(
                new Book("123",
                        new String[]{"Hemang"},
                        "ShivDhara",
                        "Java8",
                        new Date().toString()),

                new Book("124",
                        new String[]{"Hemang", "Nikunj"},
                        "Forever Creative Soft",
                        "Programming",
                        new Date().toString()),

                new Book("125",
                        new String[]{"Tushar", "Nikunj", "Hemang"},
                        "Ahmedabad",
                        "Machine Learning",
                        new Date().toString())
        ).forEach(bookRepository::save);
    }

    private RuntimeWiring getRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query",
                        typeWiring ->
                                typeWiring
                                        .dataFetcher("getAllBooks", allBooksFetcher)
                                        .dataFetcher("getBook", byIsbnFetcher))
                .build();
    }

}
