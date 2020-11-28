package com.techrevolution.graphql.graphqlwithhsqldb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    @Id
    private String isbn;
    private String[] authors;
    private String publisher;
    private String bookTitle;
    private String publishedDate;
}
