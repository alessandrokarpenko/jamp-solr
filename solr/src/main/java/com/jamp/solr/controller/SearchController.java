package com.jamp.solr.controller;

import com.jamp.solr.model.BookSearchResults;
import com.jamp.solr.model.SearchQueryDto;
import com.jamp.solr.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/search/book")
@Tag(name = "Search service", description = "API search books")
public class SearchController {

    @Autowired()
    private SearchService searchService;

    @PostMapping()
    @Operation(summary = "Search")
    public ResponseEntity<BookSearchResults> search(@RequestBody SearchQueryDto searchQueryDto) {
        return ResponseEntity.ok(searchService.search(searchQueryDto));
    }

}