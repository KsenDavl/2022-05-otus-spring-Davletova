package ru.otus.spring.davlks.service;

import ru.otus.spring.davlks.common.QuizResults;
import ru.otus.spring.davlks.model.Quiz;

public interface QuizService {

    Quiz getQuiz();

    int askQuestionsAndCountCorrectAnswers(Quiz quiz);

    QuizResults getResult(int questionsNumber, int correctAnswers, int correctAnswersRequired);

}
