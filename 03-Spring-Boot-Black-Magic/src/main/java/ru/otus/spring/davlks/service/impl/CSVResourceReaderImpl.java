package ru.otus.spring.davlks.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.common.LocaleHandler;
import ru.otus.spring.davlks.service.CSVResourceReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVResourceReaderImpl implements CSVResourceReader {

    private ResourceLoader resourceLoader;

    public CSVResourceReaderImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<List<String>> getCsvAsList() {
        String classpath = LocaleHandler.getMessage("csv-classpath");
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
