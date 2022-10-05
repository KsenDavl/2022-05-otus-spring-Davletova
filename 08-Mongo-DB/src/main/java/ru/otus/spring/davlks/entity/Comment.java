package ru.otus.spring.davlks.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Comment {

    private String text;

    @Override
    public String toString() {
        return String.format("-\"%s\"", text);
    }
}
