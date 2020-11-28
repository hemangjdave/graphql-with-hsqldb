package com.techrevolution.graphql.graphqlwithhsqldb.repository;

import com.techrevolution.graphql.graphqlwithhsqldb.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book , String> {
}
