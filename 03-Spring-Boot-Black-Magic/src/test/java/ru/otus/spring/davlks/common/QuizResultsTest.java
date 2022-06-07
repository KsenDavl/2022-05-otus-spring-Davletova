package ru.otus.spring.davlks.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Метод получения результата викторины ")
class QuizResultsTest {

    private final int questionsNumber = 10;
    private final int correctAnswersRequired = 5;

    @Test
    @DisplayName( "возвращает EXCELLENT если все ответы правильные")
    void shouldReturnExcellentWhenAllAnswersAreCorrect() {
        int correctAnswers = 10;
        QuizResults results = QuizResults.getResult(questionsNumber, correctAnswers, correctAnswersRequired);
        assertEquals(QuizResults.EXCELLENT, results);
    }

    @Test
    @DisplayName( "возвращает GOOD если правильных ответов больше или столько, сколько требуется")
    void shouldReturnGoodWhenCorrectAnswersAreMoreThanRequired() {
        int correctAnswers = 6;
        QuizResults results = QuizResults.getResult(questionsNumber, correctAnswers, correctAnswersRequired);
        assertEquals(QuizResults.GOOD, results);
    }

    @Test
    @DisplayName( "возвращает POOR если правильных ответов меньше, чем требуется")
    void shouldReturnPoorWhenCorrectAnswersLessThanRequired() {
        int correctAnswers = 3;
        QuizResults results = QuizResults.getResult(questionsNumber, correctAnswers, correctAnswersRequired);
        assertEquals(QuizResults.POOR, results);
    }
}