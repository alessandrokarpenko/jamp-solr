package com.jamp.solr.storage;

import com.jamp.solr.model.Book;
import com.jamp.solr.mapper.EpubBookToBookMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import nl.siegmann.epublib.epub.EpubReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocalFolderBookRepository implements BookRepository {

    private final EpubReader epubReader = new EpubReader();
    @Autowired
    private EpubBookToBookMapper epubBookToBookMapper;

    @SneakyThrows
    @Override
    public Set<Book> findAllBooks(String source) {
        return Files.walk(Paths.get(source))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .map(this::toBook)
                .collect(Collectors.toSet());
    }

    private Book toBook(File file) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            return epubBookToBookMapper.toBook(epubReader.readEpub(inputStream), file.getName());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}



















