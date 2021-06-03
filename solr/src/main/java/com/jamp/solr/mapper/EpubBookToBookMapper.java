package com.jamp.solr.mapper;

import com.jamp.solr.model.Book;
import lombok.SneakyThrows;
import nl.siegmann.epublib.domain.Author;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EpubBookToBookMapper {

    @SneakyThrows
    public Book toBook(nl.siegmann.epublib.domain.Book epubBook, String fileName) {
        var metaData = epubBook.getMetadata();
        var book = new Book();
        var titles = metaData.getTitles();
        book.setId(fileName);
        book.setTitle(titles.isEmpty() ? fileName : titles.get(0));
        book.setLanguage(metaData.getLanguage());

        var authors = metaData.getAuthors().stream()
                .map(Author::toString)
                .map(author -> author.replace(",", ""))
                .collect(Collectors.toList());
        book.setAuthors(authors);

        var builder = new StringBuilder(1000000);
        for (var content: epubBook.getContents()) {
            String doc = new String(content.getData());
            builder.append(Jsoup.parse(doc).text());
        }
        book.setContent(builder.toString());

        return book;
    }
}

















