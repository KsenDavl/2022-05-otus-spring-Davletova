package ru.otus.spring.davlks.dto;

import lombok.Data;
import ru.otus.spring.davlks.entity.Author;
import ru.otus.spring.davlks.entity.Genre;

@Data
public class BookDto {

    private Long id;

    private String title;

    private Author author;

    private Genre genre;

}
