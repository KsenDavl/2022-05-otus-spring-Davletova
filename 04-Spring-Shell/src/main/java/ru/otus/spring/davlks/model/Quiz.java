package ru.otus.spring.davlks.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Quiz {

    private List<Question> questions;
}
