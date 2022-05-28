package ru.otus.spring.davlks.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.common.Entries;
import ru.otus.spring.davlks.model.Quiz;
import ru.otus.spring.davlks.service.CSVResourceReader;
import ru.otus.spring.davlks.service.ConsoleReader;
import ru.otus.spring.davlks.service.ConsoleWriter;
import ru.otus.spring.davlks.service.QuizService;

@Data
@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private CSVResourceReader resourceReader;

    private ConsoleWriter consoleWriter;

    private ConsoleReader consoleReader;

    @Override
    public void runQuiz() {
        consoleWriter.write(Entries.QUIZ.getEntryText());
        Quiz quiz = resourceReader.readQuiz();
        quiz.getQuestions().forEach(question -> {
            consoleWriter.writeQuestionAndAnswers(question);
            consoleWriter.write("Type your answer:");
            String studentAnswer = consoleReader.read();
            consoleWriter.write(String.format("Your answer is '%s' and the right answer is '%s'", studentAnswer, question.getCorrectAnswer()));
        });
        consoleWriter.write("Good job!");

    }

}
