package ru.otus.spring.davlks.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.davlks.model.Question;
import ru.otus.spring.davlks.model.Quiz;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("Класс QuizService ")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

    @Mock
    private CSVResourceReaderImpl resourceReader;
    @Mock
    private ConsoleServiceImpl consoleService;

    @BeforeEach
    void init() {
        List<List<String>> csvAsList = new ArrayList<>();
        List<String> firstLine = new ArrayList<>();
        firstLine.add("1. Which of these messengers has the biggest number of active users in the world?");
        firstLine.add(" a)Facebook messenger");
        firstLine.add(" b)Viber");
        firstLine.add(" c)WhatsApp");
        firstLine.add(" d)Telegram");
        firstLine.add("d");
        csvAsList.add(firstLine);
        given(resourceReader.getCsvAsList()).willReturn(csvAsList);
    }

//    @Test
//    @DisplayName("получает викторину из списка вопросов")
//    void getQuiz() {
//        QuizServiceImpl quizService = new QuizServiceImpl(0, 0,
//                consoleService, resourceReader);
//        Quiz quiz = quizService.getQuiz();
//        Question firstQuestion = quiz.getQuestions().get(0);
//
//        assertEquals("d", firstQuestion.getCorrectAnswer());
//    }
}