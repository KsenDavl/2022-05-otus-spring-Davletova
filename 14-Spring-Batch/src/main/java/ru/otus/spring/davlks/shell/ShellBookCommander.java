package ru.otus.spring.davlks.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
class ShellBookCommander {

    private final Job migrateBooksJob;

    private final JobLauncher jobLauncher;

     @ShellMethod(value = "startMigrationJobWithJobLauncher", key = "start")
    public void startMigrationJobWithJobLauncher() throws Exception {
         jobLauncher.run(migrateBooksJob, new JobParametersBuilder().toJobParameters());
    }

}
