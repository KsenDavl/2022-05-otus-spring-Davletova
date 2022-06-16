package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.model.Quiz;

import java.io.IOException;

public interface QuizService {

    Quiz getQuiz();

    void runQuiz() throws IOException;
}
