package ru.otus.spring.davlks.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import ru.otus.spring.davlks.model.Question;
import ru.otus.spring.davlks.model.Quiz;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс CSVResourceReader")
class CSVResourceReaderImplTest {

    private static CSVResourceReaderImpl csvResourceReader;

    @BeforeAll
    static void initializeService() {
        csvResourceReader = new CSVResourceReaderImpl(new DefaultResourceLoader(), "classpath:testQuiz.csv");
    }

    @Test
    @DisplayName("корректно считывает викторину")
    void whenResourceLoaderReadCsvGetNotEmptyQuizObject()  {
        Quiz quiz = csvResourceReader.readQuiz();
        List<Question> questions = quiz.getQuestions();
        assertEquals(1, questions.size());

        Question firstQuestion = questions.get(0);
        assertEquals(4, firstQuestion.getAnswers().length);
        assertEquals("c", firstQuestion.getCorrectAnswer());
    }
}