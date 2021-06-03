package com.jamp.solr.model;

import lombok.Data;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;

import java.util.Collections;
import java.util.List;

@Data
public class BookSearchResults {
    private List<BookDto> books;
    private List<SimpleFacetFieldEntry> facets;
    private long numFound;
    private int perPage;

    public BookSearchResults(List<BookDto> books, List<SimpleFacetFieldEntry> facets) {
        this.books = books;
        this.facets = facets;
    }

    public static BookSearchResults empty() {
        return new BookSearchResults(Collections.emptyList(), Collections.emptyList());
    }
}