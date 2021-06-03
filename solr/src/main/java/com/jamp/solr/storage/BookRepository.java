package com.jamp.solr.storage;

import com.jamp.solr.model.Book;

import java.util.Set;

public interface BookRepository {

    public Set<Book> findAllBooks(String source);
}
