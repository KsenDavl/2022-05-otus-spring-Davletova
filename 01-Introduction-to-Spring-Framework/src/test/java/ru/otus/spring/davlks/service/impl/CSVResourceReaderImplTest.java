package ru.otus.spring.davlks.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
//

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring-test-context.xml")
@DisplayName("Класс CSVResourceReader")
public class CSVResourceReaderImplTest {
    
    private CSVResourceReaderImpl csvResourceReader;

    public CSVResourceReaderImplTest(CSVResourceReaderImpl csvResourceReader) {
        this.csvResourceReader = csvResourceReader;
    }

    @Test
    @DisplayName("корректно считывает викторину")
    void whenResourceLoaderReadCsvGetNotEmptyQuizObject()  {
        assertNotNull(csvResourceReader);
        //Quiz quiz = csvResourceReader.readQuiz();
    }
}