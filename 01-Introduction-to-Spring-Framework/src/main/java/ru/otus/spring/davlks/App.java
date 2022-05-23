package ru.otus.spring.davlks;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.davlks.service.impl.QuizServiceImpl;

public class App {

    public static void main( String[] args ) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizServiceImpl service = context.getBean(QuizServiceImpl.class);
        service.runQuiz();
    }
}
