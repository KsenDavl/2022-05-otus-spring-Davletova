package ru.otus.spring.davlks.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.service.ConsoleReader;

import java.util.Scanner;

@Data
@Service
public class ConsoleReaderImpl implements ConsoleReader {

    Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        String input = null;
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
}
