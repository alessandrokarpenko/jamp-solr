package com.jamp.solr.model;

import lombok.Data;

@Data
public class SearchQueryDto {

    private String field = "*";
    private String value = "*";
    private String facetField;
    private boolean fullText;
    private String q;

}
