package ru.otus.spring.davlks;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.davlks.service.impl.QuizServiceImpl;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class App {

    public static void main( String[] args ) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(App.class);
        QuizServiceImpl service = context.getBean(QuizServiceImpl.class);
        service.runQuiz();
    }
}
