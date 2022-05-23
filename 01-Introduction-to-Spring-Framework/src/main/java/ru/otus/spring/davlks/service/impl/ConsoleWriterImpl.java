package ru.otus.spring.davlks.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.spring.davlks.model.Question;
import ru.otus.spring.davlks.service.ConsoleWriter;

import java.util.Arrays;

@Data
@AllArgsConstructor
public class ConsoleWriterImpl implements ConsoleWriter {

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public void writeQuestionAndAnswers(Question question) {
        System.out.println("\n" + question.getQuestionBody());
        Arrays.stream(question.getAnswers()).forEach(System.out::println);
    }
}
