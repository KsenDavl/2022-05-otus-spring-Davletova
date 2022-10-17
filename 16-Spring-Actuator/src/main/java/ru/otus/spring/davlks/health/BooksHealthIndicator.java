package ru.otus.spring.davlks.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.spring.davlks.dao.BookDao;

@Component
public class BooksHealthIndicator implements HealthIndicator {

    private final BookDao bookDao;

    public BooksHealthIndicator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public Health health() {

        if (bookDao.findAll().isEmpty()) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "No books info in database!")
                    .build();
        } else {
            return Health.up().build();
        }

    }
}
