package ru.otus.spring.davlks.common;

public enum QuizResults {

    EXCELLENT,
    GOOD,
    POOR;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
