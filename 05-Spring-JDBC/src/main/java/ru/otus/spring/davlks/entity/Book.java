package ru.otus.spring.davlks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private long id;

    private String title;

    private Author author;

    private Genre genre;

    @Override
    public String toString() {
        return String.format("%d. \"%s\", %s, by %s %s",
                id, title, genre.getName(), author.getLastName(), author.getFirstName());
    }
}
