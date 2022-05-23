package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.model.Question;

public interface ConsoleWriter {

    void write(String string);

    void writeQuestionAndAnswers(Question question);

}
