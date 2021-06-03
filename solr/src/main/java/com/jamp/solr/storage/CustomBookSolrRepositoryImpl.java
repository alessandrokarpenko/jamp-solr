package com.jamp.solr.storage;

import com.jamp.solr.model.Book;
import com.jamp.solr.model.BookSearchResults;
import com.jamp.solr.model.SearchQueryDto;
import com.jamp.solr.mapper.BookToSearchResultMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;

import java.util.List;

@RequiredArgsConstructor
public class CustomBookSolrRepositoryImpl implements CustomBookSolrRepository {

    @Value("${solr.collection}")
    private String solrCollection;
    @Autowired
    private SolrTemplate solrTemplate;
    @Autowired
    private final BookToSearchResultMapper bookToSearchResultMapper;

    @Override
    public BookSearchResults searchBook(SearchQueryDto searchQueryDto) {
        var query = buildQuery(searchQueryDto);
        var facetQuery = buildFacetQuery(searchQueryDto, query);
        if (searchQueryDto.isFullText()) {
            facetQuery.addCriteria(createSearchConditions(searchQueryDto.getQ().split(" ")));
        }
        return bookToSearchResultMapper.toSearchResults(
                solrTemplate.queryForFacetPage(solrCollection, facetQuery, Book.class));
    }

    @Override
    public List<String> suggestTitle(String query) {
        return null;
    }

    private Query buildQuery(SearchQueryDto searchQueryDto) {
        var value = searchQueryDto.getValue().trim();
        var field = searchQueryDto.getField().trim();
        if ("*".equals(value) || "*".equals(field)) {
            var query = Query.all();
            query.setRows(12);
            return query;
        }
        return Query.query(Criteria.where(field).is(value));
    }

    private FacetQuery buildFacetQuery(SearchQueryDto searchQueryDto, Query query) {
        String facetField = searchQueryDto.getFacetField();
        if (facetField != null) {
            var facetQuery = SimpleFacetQuery.fromQuery(query, new SimpleFacetQuery());
            return facetQuery.setFacetOptions(new FacetOptions(facetField));
        }
        return SimpleFacetQuery.fromQuery(query, new SimpleFacetQuery());
    }

    private Criteria createSearchConditions(String[] words) {
        return Criteria.where("title").boost(4).contains(words)
                .or(Criteria.where("authors")).boost(3).contains(words)
                .or(Criteria.where("content")).contains(words);
    }
}