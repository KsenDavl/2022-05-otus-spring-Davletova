package ru.otus.spring.davlks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private String questionBody;

    private List<String> answers;

    private String correctAnswer;

}
