package com.jamp.solr.mapper;

import com.jamp.solr.model.Book;
import com.jamp.solr.model.BookSearchResults;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.SimpleFacetFieldEntry;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookToSearchResultMapper {

    @Autowired
    private BookToDtoMapper bookToDtoMapper;

    public BookSearchResults toSearchResults(FacetPage<Book> solrResults) {
        var books = bookToDtoMapper.convertAll(solrResults.getContent());
        var facets = solrResults.getFacetResultPages().stream()
                .map(Page::getContent)
                .flatMap(Collection::stream)
                .map(SimpleFacetFieldEntry.class::cast)
                .collect(Collectors.toList());

        var searchResults = new BookSearchResults(books, facets);
        searchResults.setNumFound(solrResults.getNumberOfElements());
        return searchResults;
    }
}