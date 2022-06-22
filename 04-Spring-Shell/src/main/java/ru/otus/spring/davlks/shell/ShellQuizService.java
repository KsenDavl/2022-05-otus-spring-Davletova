package ru.otus.spring.davlks.shell;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.common.LocaleHandler;
import ru.otus.spring.davlks.common.QuizResults;
import ru.otus.spring.davlks.model.Quiz;
import ru.otus.spring.davlks.service.ConsoleService;
import ru.otus.spring.davlks.service.QuizService;

@Service
public class ShellQuizService {

    private final int correctAnswersRequired;
    private int correctAnswers = -1;
    private int numberOfQuestions;
    private String name;

    private final QuizService quizService;
    private final ConsoleService consoleService;

    public ShellQuizService(@Value("${quiz.questions.required}") int correctAnswersRequired,
                            QuizService quizService,
                            ConsoleService consoleService) {
        this.correctAnswersRequired = correctAnswersRequired;
        this.quizService = quizService;
        this.consoleService = consoleService;
    }

    public String getQuizIntroduction(String name) {
        this.name = name;
        return String.format(LocaleHandler.getMessage("entry"), name);
    }

    public void runQuiz() {
        Quiz quiz = quizService.getQuiz();
        numberOfQuestions = quiz.getQuestions().size();
        correctAnswers = quizService.askQuestionsAndCountCorrectAnswers(quiz);
    }

    public void writeResult() {
        if (correctAnswers == -1) {
            consoleService.write(LocaleHandler.getMessage("no-quiz-result"));
            return;
        }
        consoleService.write(String.format(LocaleHandler.getMessage("correct-answers-amount"), name,  correctAnswers));
        QuizResults result = quizService.getResult(numberOfQuestions, correctAnswers, correctAnswersRequired);
        consoleService.write(LocaleHandler.getMessage(result.toString()));
    }

    public void runFullQuiz() {
        if (name == null) {
            consoleService.write(LocaleHandler.getMessage("greeting"));
            name = consoleService.read();
        }
        consoleService.write(String.format(LocaleHandler.getMessage("entry"), name));
        Quiz quiz = quizService.getQuiz();

        numberOfQuestions = quiz.getQuestions().size();
        correctAnswers = quizService.askQuestionsAndCountCorrectAnswers(quiz);

        consoleService.write(String.format(LocaleHandler.getMessage("correct-answers-amount"), name,  correctAnswers));
        QuizResults result = quizService.getResult(numberOfQuestions, correctAnswers, correctAnswersRequired);
        consoleService.write(LocaleHandler.getMessage(result.toString()));
    }
}
