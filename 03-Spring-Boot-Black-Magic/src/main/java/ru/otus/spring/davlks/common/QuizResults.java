package ru.otus.spring.davlks.common;

public enum QuizResults {

    EXCELLENT("You've done an excellent job! All answers are correct!"),
    GOOD("Good job! You've passed the test!"),
    POOR("Not bad, but you should try again and improve your result!");

    private final String description;

    QuizResults(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static QuizResults getResult(int questionsNumber, int correctAnswers, int correctAnswersRequired) {
        if (questionsNumber == correctAnswers) {
            return QuizResults.EXCELLENT;
        }
        if (correctAnswers < correctAnswersRequired) {
            return QuizResults.POOR;
        }
        return QuizResults.GOOD;
    }
}
