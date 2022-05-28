package ru.otus.spring.davlks.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.model.Question;
import ru.otus.spring.davlks.model.Quiz;
import ru.otus.spring.davlks.service.CSVResourceReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
@AllArgsConstructor
public class CSVResourceReaderImpl implements CSVResourceReader {

    private ResourceLoader resourceLoader;

    private String classpath;

    @Override
    public Quiz readQuiz() {

        Resource resource = resourceLoader.getResource(classpath);
        Quiz quiz = new Quiz();

        try (InputStream inputStream = resource.getInputStream();
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] lineInArray;
            List<Question> questions = new ArrayList<>();
            while ((lineInArray = reader.readNext()) != null) {
                questions.add(getQuestion(lineInArray));
            }
            quiz.setQuestions(questions);
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return quiz;
    }

    private Question getQuestion(String[] line) {
        Question question = new Question();

        question.setQuestionBody(line[0]);
        question.setAnswers(new String[]{line[1], line[2], line[3], line[4]});
        question.setCorrectAnswer(line[5]);

        return question;
    }

}
