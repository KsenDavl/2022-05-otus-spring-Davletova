package ru.otus.spring.davlks.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.spring.davlks.service.ConsoleService;

import java.util.Scanner;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String read() {
        String input = null;
        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        }
        return input;
    }
    @Override
    public void write(String string) {
        System.out.println(string);
    }
}
