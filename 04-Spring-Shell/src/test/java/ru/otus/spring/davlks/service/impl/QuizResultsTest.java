package ru.otus.spring.davlks.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import ru.otus.spring.davlks.common.QuizResults;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Метод получения результата викторины в QuizService")
class QuizResultsTest {

    private final int questionsNumber = 10;
    private final int correctAnswersRequired = 5;

    @Mock
    private CSVResourceReaderImpl resourceReader;
    @Mock
    private ConsoleServiceImpl consoleService;

    private QuizServiceImpl quizService;

    @BeforeEach
    public void init() {
        quizService = new QuizServiceImpl(0, 0, consoleService, resourceReader);
    }

    @Test
    @DisplayName("возвращает EXCELLENT если все ответы правильные")
    void shouldReturnExcellentWhenAllAnswersAreCorrect() {
        int correctAnswers = 10;
        QuizResults results = quizService.getResult(questionsNumber, correctAnswers, correctAnswersRequired);
        assertEquals(QuizResults.EXCELLENT, results);
    }

    @Test
    @DisplayName("возвращает GOOD если правильных ответов больше или столько, сколько требуется")
    void shouldReturnGoodWhenCorrectAnswersAreMoreThanRequired() {
        int correctAnswers = 6;
        QuizResults results = quizService.getResult(questionsNumber, correctAnswers, correctAnswersRequired);
        assertEquals(QuizResults.GOOD, results);
    }

    @Test
    @DisplayName("возвращает POOR если правильных ответов меньше, чем требуется")
    void shouldReturnPoorWhenCorrectAnswersLessThanRequired() {
        int correctAnswers = 3;
        QuizResults results = quizService.getResult(questionsNumber, correctAnswers, correctAnswersRequired);
        assertEquals(QuizResults.POOR, results);
    }
}