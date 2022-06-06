package ru.otus.spring.davlks.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.model.Question;
import ru.otus.spring.davlks.service.ConsoleWriter;

@Data
@Service
@AllArgsConstructor
public class ConsoleWriterImpl implements ConsoleWriter {

    @Override
    public void write(String string) {
        System.out.println(string);
    }

    @Override
    public void writeQuestionAndAnswers(Question question) {
        System.out.println("\n" + question.getQuestionBody());
        question.getAnswers().forEach(System.out::println);
    }

}
