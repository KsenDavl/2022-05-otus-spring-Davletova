package ru.otus.spring.davlks.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class LocaleHandler {

    private static String language;

    private static MessageSource messageSource;

    public LocaleHandler(@Value("${quiz.language}") String language,
                         MessageSource messageSource) {
        LocaleHandler.language = language;
        LocaleHandler.messageSource = messageSource;
    }

    public static String getMessage(String code) {
        return messageSource.getMessage(code, null, new Locale(language));
    }
}
