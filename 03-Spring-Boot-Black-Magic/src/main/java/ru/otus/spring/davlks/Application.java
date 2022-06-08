package ru.otus.spring.davlks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.davlks.service.impl.QuizServiceImpl;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        QuizServiceImpl service = context.getBean(QuizServiceImpl.class);
        service.runQuiz();
    }
}
