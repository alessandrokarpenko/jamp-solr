package com.jamp.solr.storage;

import com.jamp.solr.model.Book;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface BookSolrRepository extends SolrCrudRepository<Book, String>, CustomBookSolrRepository {

}
