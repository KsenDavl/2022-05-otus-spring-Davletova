package ru.otus.spring.davlks.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.common.Entries;
import ru.otus.spring.davlks.common.QuizResults;
import ru.otus.spring.davlks.model.Question;
import ru.otus.spring.davlks.model.Quiz;
import ru.otus.spring.davlks.service.CSVResourceReader;
import ru.otus.spring.davlks.service.ConsoleReader;
import ru.otus.spring.davlks.service.ConsoleWriter;
import ru.otus.spring.davlks.service.QuizService;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class QuizServiceImpl implements QuizService {

    private final int correctAnswersRequired;
    private final int attempts;

    private ConsoleWriter consoleWriter;
    private ConsoleReader consoleReader;
    private CSVResourceReader resourceReader;

    public QuizServiceImpl(@Value("${quiz.questions.required}") int correctAnswersRequired,
                           @Value("${quiz.questions.attempt}") int attempts,
                           ConsoleWriter consoleWriter,
                           ConsoleReader consoleReader,
                           CSVResourceReader resourceReader) {
        this.correctAnswersRequired = correctAnswersRequired;
        this.attempts = attempts;
        this.consoleWriter = consoleWriter;
        this.consoleReader = consoleReader;
        this.resourceReader = resourceReader;
    }

    @Override
    public void runQuiz() {
        consoleWriter.write("Hello! Please, type up your last name and first name");
        String name = consoleReader.read();
        consoleWriter.write(name + ", " + Entries.QUIZ.getEntryText());
        Quiz quiz = getQuiz();
        int correctAnswers = askQuestionsAndCountCorrectAnswers(quiz);
        consoleWriter.write(String.format("%nThat's it, %s! You've got %d correct answer(s)!", name, correctAnswers));
        QuizResults result = QuizResults.getResult(quiz.getQuestions().size(), correctAnswers, correctAnswersRequired);
        consoleWriter.write(result.getDescription());
    }

    @Override
    public Quiz getQuiz() {
        List<List<String>> quizFromCsv = resourceReader.getCsvAsList();
        Quiz quiz = new Quiz();
        List<Question> questions = new ArrayList<>();

        quizFromCsv.stream().forEach(line -> {
            Question question = getQuestion(line);
            questions.add(question);
        });
        quiz.setQuestions(questions);
        return quiz;

    }

    private Question getQuestion(List<String> line) {
        Question question = new Question();

        question.setQuestionBody(line.get(0));
        question.setAnswers(List.of(line.get(1), line.get(2), line.get(3), line.get(4)));
        question.setCorrectAnswer(line.get(5));

        return question;
    }

    private int askQuestionsAndCountCorrectAnswers(Quiz quiz) {
        int correctAnswers = 0;

        for (Question question : quiz.getQuestions()) {
            int attemptsToAnswer = attempts;
            consoleWriter.writeQuestionAndAnswers(question);
            consoleWriter.write("Type your answer:");
            String answer = getAnswer();

            if (isAnswerCorrect(answer, question.getCorrectAnswer())) {
                correctAnswers++;
            } else {
                while (!isAnswerCorrect(answer, question.getCorrectAnswer()) && attemptsToAnswer > 1) {
                    attemptsToAnswer--;
                    consoleWriter.write("The answer is incorrect, you've got " + attemptsToAnswer + " more attempt(s)");
                    answer = getAnswer();
                }
            }
            consoleWriter.write("The correct answer is " + question.getCorrectAnswer());
        }
        return correctAnswers;
    }

    private boolean isAnswerCorrect(String givenAnswer, String correctAnswer) {
        return correctAnswer.equals(givenAnswer);
    }

    private String getAnswer() {
        String answer = consoleReader.read();
        while (!isAnswerValid(answer)) {
            consoleWriter.write("The answer should consist of one letter: 'a' or 'b' or 'c' or 'd'");
            answer = consoleReader.read();
        }
        return answer;
    }

    private boolean isAnswerValid(String answer) {
        return answer.matches("[a-d]");
    }

}
