package ru.otus.spring.davlks.service;

import java.util.List;

public interface CSVResourceReader {

    List<List<String>> getCsvAsList();
}
