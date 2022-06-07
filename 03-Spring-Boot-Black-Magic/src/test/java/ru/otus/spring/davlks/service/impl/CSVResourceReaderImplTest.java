package ru.otus.spring.davlks.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс CSVResourceReader")
class CSVResourceReaderImplTest {

    private static CSVResourceReaderImpl csvResourceReader;

//    @BeforeAll
//    static void initializeService() {
//        csvResourceReader = new CSVResourceReaderImpl("classpath:testQuiz.csv",
//                new DefaultResourceLoader());
//    }

    @Test
    @DisplayName("корректно считывает csv файл")
    void whenResourceLoaderReadCsvGetCorrectObject()  {
        List<List<String>> csvAsList = csvResourceReader.getCsvAsList();

        assertEquals(1, csvAsList.size());

        List<String> firstLine = csvAsList.get(0);
        assertEquals(6, firstLine.size());
        assertEquals("c", firstLine.get(5));
    }
}