package ru.otus.spring.davlks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class MongoBook {

    @Id
    private String id;

    private String title;

    private MongoAuthor mongoAuthor;

    private MongoGenre mongoGenre;

    private List<MongoComment> mongoComments;

}
