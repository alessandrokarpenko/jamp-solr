package com.jamp.solr.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookDto {
    private String id;
    private List<String> authors;
    private String title;
    private String language;
}
