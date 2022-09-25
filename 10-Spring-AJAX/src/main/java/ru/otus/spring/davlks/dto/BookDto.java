package ru.otus.spring.davlks.dto;

import lombok.Data;

@Data
public class BookDto {

    private Long id;

    private String title;

    private String authorLastName;

    private String authorFirstName;

    private String genre;

}
