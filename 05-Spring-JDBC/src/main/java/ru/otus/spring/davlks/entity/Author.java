package ru.otus.spring.davlks.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private long id;

    private String lastName;

    private String firstName;

    @Override
    public String toString() {
        return id + ". " + lastName + " " + firstName;
    }
}
