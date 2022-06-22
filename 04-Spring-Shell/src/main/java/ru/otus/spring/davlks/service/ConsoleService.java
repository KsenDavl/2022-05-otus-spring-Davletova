package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.model.Question;

public interface ConsoleService {

    String read();

    void write(String string);

    void writeQuestionAndAnswers(Question question);
}
