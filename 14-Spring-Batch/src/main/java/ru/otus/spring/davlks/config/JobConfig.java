package ru.otus.spring.davlks.config;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import ru.otus.spring.davlks.converter.BookConverter;
import ru.otus.spring.davlks.dao.BookDao;
import ru.otus.spring.davlks.dao.MongoBookRepository;
import ru.otus.spring.davlks.entity.Book;
import ru.otus.spring.davlks.entity.MongoBook;

import java.util.HashMap;


@Configuration
@AllArgsConstructor
public class JobConfig {
    private static final int CHUNK_SIZE = 5;

    public static final String IMPORT_USER_JOB_NAME = "migrateBooksJob";

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final MongoBookRepository mongoBookRepository;
    private final BookDao bookDao;
    private final BookConverter converter;

    @Bean
    public RepositoryItemReader<MongoBook> reader() {
        RepositoryItemReader<MongoBook> reader = new RepositoryItemReader<>();
        reader.setRepository(mongoBookRepository);
        reader.setMethodName("findAll");
        reader.setSort(new HashMap<String, Sort.Direction>() {
            {
                put("_id", Sort.Direction.ASC);
            }
        });
        return reader;
    }

    @StepScope
    @Bean
    public ItemProcessor<MongoBook, Book> processor() {
        return converter::convertBook;
    }

    @Bean
    public RepositoryItemWriter<Book> writer() {
        RepositoryItemWriter<Book> writer = new RepositoryItemWriter<>();
        writer.setRepository(bookDao);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public Job migrateBooksJob(Step migrateBooksStep) {
        return jobBuilderFactory.get(IMPORT_USER_JOB_NAME)
                .start(migrateBooksStep)
                .build();
    }

    @Bean
    public Step migrateBooksStep(ItemReader<MongoBook> reader, ItemWriter<Book> writer,
                                 ItemProcessor<MongoBook, Book> itemProcessor) {
        return stepBuilderFactory.get("step1")
                .<MongoBook, Book>chunk(CHUNK_SIZE)
                .reader(reader)
                .processor(itemProcessor)
                .writer(writer)
                .build();
    }

}
