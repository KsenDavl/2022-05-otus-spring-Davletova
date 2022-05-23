package ru.otus.spring.davlks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private String questionBody;

    private String[] answers;

    private String correctAnswer;

}
