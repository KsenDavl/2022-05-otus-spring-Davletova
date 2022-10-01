package ru.otus.spring.davlks.dto;

import lombok.Data;

@Data
public class CommentDto {

    private long id;

    private String text;

    private Long bookId;
}
