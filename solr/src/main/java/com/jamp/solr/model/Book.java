package com.jamp.solr.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@SolrDocument(collection = "books")
@Data
public class Book {
    @Id
    @Indexed
    private String id;
    @Indexed
    private List<String> authors;
    @Indexed
    private String title;
    @Indexed
    private String language;
    @Indexed
    private String content;
}
















