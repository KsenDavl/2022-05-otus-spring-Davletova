package ru.otus.spring.davlks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Quiz {

    private String quizEntry;

    private List<Question> questions;
}
