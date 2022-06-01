package ru.otus.spring.davlks.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.service.CSVResourceReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class CSVResourceReaderImpl implements CSVResourceReader {

    private ResourceLoader resourceLoader;

    private String classpath;

    public CSVResourceReaderImpl(@Value("${quiz.file.path}") String classpath,
                                 ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.classpath = classpath;
    }

    @Override
    public List<List<String>> getCsvAsList() {
        Resource resource = resourceLoader.getResource(classpath);
        List<List<String>> csvAsList = new ArrayList<>();

        try (InputStream inputStream = resource.getInputStream();
             CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] lineAsArray;
            while ((lineAsArray = reader.readNext()) != null) {
                csvAsList.add(List.of(lineAsArray));
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return csvAsList;
    }
}
