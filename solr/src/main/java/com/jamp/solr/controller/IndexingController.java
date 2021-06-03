package com.jamp.solr.controller;

import com.jamp.solr.service.IndexingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/index")
@Tag(name = "Indexing service", description = "API upload and remove books")
public class IndexingController {

    @Autowired()
    private IndexingService indexingService;

    @PostMapping()
    @Operation(summary = "Index books from local folder")
    public void indexBooks() {
        indexingService.index();
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete books from solr collection")
    public void deleteBooks() {
        indexingService.removeAll();
    }
}
