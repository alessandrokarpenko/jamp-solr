package com.jamp.solr.service;

import com.jamp.solr.model.BookSearchResults;
import com.jamp.solr.model.SearchQueryDto;
import com.jamp.solr.storage.CustomBookSolrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    @Qualifier("customBookSolrRepositoryImpl")
    private CustomBookSolrRepository customBookSolrRepository;

    public BookSearchResults search(SearchQueryDto searchQueryDto) {
        return customBookSolrRepository.searchBook(searchQueryDto);
    }
}
