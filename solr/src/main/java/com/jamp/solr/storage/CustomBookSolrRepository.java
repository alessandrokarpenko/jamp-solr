package com.jamp.solr.storage;

import com.jamp.solr.model.BookSearchResults;
import com.jamp.solr.model.SearchQueryDto;

import java.util.List;

public interface CustomBookSolrRepository {

    BookSearchResults searchBook(SearchQueryDto searchQueryDto);

    List<String> suggestTitle(String query);
}
