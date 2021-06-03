package com.jamp.solr.mapper;

import com.jamp.solr.model.Book;
import com.jamp.solr.model.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookToDtoMapper {

    public List<BookDto> convertAll(List<Book> content) {
        return content.stream()
                .map(x->BookDto.builder()
                        .id(x.getId())
                        .authors(x.getAuthors())
                        .title(x.getTitle())
                        .language(x.getLanguage()).build())
                .collect(Collectors.toList());
    }
}
