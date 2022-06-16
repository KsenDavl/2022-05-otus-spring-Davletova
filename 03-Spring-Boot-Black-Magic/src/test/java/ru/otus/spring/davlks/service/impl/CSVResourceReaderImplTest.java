package ru.otus.spring.davlks.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import ru.otus.spring.davlks.common.LocaleHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@DisplayName("Класс CSVResourceReader")
@ExtendWith(MockitoExtension.class)
class CSVResourceReaderImplTest {

    @Mock
    private LocaleHandler localeHandler;
    @Mock
    private MessageSource messageSource;

    private CSVResourceReaderImpl csvResourceReader;

     @BeforeEach
     void init() {
        csvResourceReader = new CSVResourceReaderImpl(new DefaultResourceLoader());
        localeHandler = new LocaleHandler("", messageSource);

        given(LocaleHandler.getMessage("csv-classpath")).willReturn("classpath:testQuiz.csv");
    }

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