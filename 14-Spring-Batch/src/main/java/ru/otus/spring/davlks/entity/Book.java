package ru.otus.spring.davlks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Author.class)
    @JoinColumn(name = "author")
    private Author author;

    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "genre")
    private Genre genre;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL, mappedBy = "book", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
