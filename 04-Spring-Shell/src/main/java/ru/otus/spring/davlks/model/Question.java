package ru.otus.spring.davlks.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class Question {

    private String questionBody;

    private List<String> answers;

    private String correctAnswer;
}
