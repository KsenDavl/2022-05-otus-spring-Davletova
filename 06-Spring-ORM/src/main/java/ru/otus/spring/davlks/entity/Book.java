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

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.REMOVE, mappedBy = "book", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @Override
    public String toString() {
        return String.format("%d. \"%s\", %s, by %s %s \nComments: \n%s",
                id, title, genre.getName(), author.getLastName(), author.getFirstName(), comments);
    }
}
