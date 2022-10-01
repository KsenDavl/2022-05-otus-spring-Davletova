package ru.otus.spring.davlks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    private List<Comment> comments;

    @Override
    public String toString() {
        return String.format("%s: \"%s\", %s, by %s %s",
                id, title, genre.getName(), author.getLastName(), author.getFirstName());
    }
}
