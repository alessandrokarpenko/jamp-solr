package com.jamp.solr.service;

import com.jamp.solr.storage.BookRepository;
import com.jamp.solr.storage.BookSolrRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndexingService {

    @Value("${books.dir}")
    private String bookSource;

    @Autowired
    private BookRepository bookLocalFolderRepository;

    @Autowired
    private BookSolrRepository bookSolrRepository;

    public void index() {
        bookSolrRepository.saveAll(bookLocalFolderRepository.findAllBooks(bookSource));
    }

    public void removeAll() {
        bookSolrRepository.deleteAll();
    }
}
