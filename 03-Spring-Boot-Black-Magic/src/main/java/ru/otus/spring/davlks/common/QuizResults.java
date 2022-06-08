package ru.otus.spring.davlks.common;

public enum QuizResults {

    EXCELLENT,
    GOOD,
    POOR;

    public static QuizResults getResult(int questionsNumber, int correctAnswers, int correctAnswersRequired) {
        if (questionsNumber == correctAnswers) {
            return QuizResults.EXCELLENT;
        }
        if (correctAnswers < correctAnswersRequired) {
            return QuizResults.POOR;
        }
        return QuizResults.GOOD;
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
