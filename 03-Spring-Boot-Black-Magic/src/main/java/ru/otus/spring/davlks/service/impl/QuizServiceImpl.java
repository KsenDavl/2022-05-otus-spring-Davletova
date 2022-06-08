package ru.otus.spring.davlks.service.impl;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.common.LocaleHandler;
import ru.otus.spring.davlks.common.QuizResults;
import ru.otus.spring.davlks.model.Question;
import ru.otus.spring.davlks.model.Quiz;
import ru.otus.spring.davlks.service.CSVResourceReader;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.QuizService;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class QuizServiceImpl implements QuizService {

    private final int correctAnswersRequired;
    private final int attempts;

    private ConsoleService consoleService;
    private CSVResourceReader resourceReader;

    public QuizServiceImpl(@Value("${quiz.questions.required}") int correctAnswersRequired,
                           @Value("${quiz.questions.attempts}") int attempts,
                           ConsoleService consoleService,
                           CSVResourceReader resourceReader) {
        this.correctAnswersRequired = correctAnswersRequired;
        this.attempts = attempts;
        this.consoleService = consoleService;
        this.resourceReader = resourceReader;
    }

    @Override
    public void runQuiz() {
        consoleService.write(LocaleHandler.getMessage("greeting"));
        String name = consoleService.read();
        consoleService.write(String.format(LocaleHandler.getMessage("entry"), name));
        Quiz quiz = getQuiz();
        int correctAnswers = askQuestionsAndCountCorrectAnswers(quiz);
        consoleService.write(String.format(LocaleHandler.getMessage("correct-answers-amount"), name,  correctAnswers));
        QuizResults result = QuizResults.getResult(quiz.getQuestions().size(), correctAnswers, correctAnswersRequired);
        consoleService.write(LocaleHandler.getMessage(result.toString()));
    }

    @Override
    public Quiz getQuiz() {
        List<List<String>> quizFromCsv = resourceReader.getCsvAsList();
        Quiz quiz = new Quiz();
        List<Question> questions = new ArrayList<>();

        quizFromCsv.forEach(line -> {
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
            consoleService.writeQuestionAndAnswers(question);
            consoleService.write("\n" + LocaleHandler.getMessage("type-answer"));
            String answer = getAnswer();

            if (isAnswerCorrect(answer, question.getCorrectAnswer())) {
                correctAnswers++;
            } else {
                while (!isAnswerCorrect(answer, question.getCorrectAnswer()) && attemptsToAnswer > 1) {
                    attemptsToAnswer--;
                    consoleService.write(String.format(LocaleHandler.getMessage("incorrect-answer"),  attemptsToAnswer));
                    answer = getAnswer();
                }
            }
            consoleService.write(LocaleHandler.getMessage("correct-answer") + question.getCorrectAnswer());
        }
        return correctAnswers;
    }

    private boolean isAnswerCorrect(String givenAnswer, String correctAnswer) {
        return correctAnswer.equals(givenAnswer);
    }

    private String getAnswer() {
        String answer = consoleService.read();
        while (!isAnswerValid(answer)) {
            consoleService.write(LocaleHandler.getMessage("answer-format"));
            answer = consoleService.read();
        }
        return answer;
    }

    private boolean isAnswerValid(String answer) {
        return answer.matches("[a-d]");
    }

}
