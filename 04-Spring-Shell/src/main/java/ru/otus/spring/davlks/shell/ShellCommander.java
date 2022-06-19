package ru.otus.spring.davlks.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class ShellCommander {

    private ShellQuizService shellQuizService;

    @ShellMethod(value = "Get quiz introduction", key = {"i", "intr"})
    public String getQuizIntroduction(String name) {
        return shellQuizService.getQuizIntroduction(name);
    }

    @ShellMethod(value = "Get quiz questions", key = {"q", "quiz"})
    public void runQuiz() {
        shellQuizService.runQuiz();
    }

    @ShellMethod(value = "Get last result", key = {"r", "result"})
    public void writeResult() {
        shellQuizService.writeResult();
    }

    @ShellMethod(value = "Get full version of quiz: with intro and result", key = {"f", "full"})
    public void runFullVersion() {
        shellQuizService.runFullQuiz();
    }

}
