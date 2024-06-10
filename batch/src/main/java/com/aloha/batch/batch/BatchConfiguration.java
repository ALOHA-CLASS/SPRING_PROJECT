package com.aloha.batch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;

import com.aloha.batch.batch.item.processor.BoardItemProcessor;
import com.aloha.batch.batch.item.reader.BoardItemReader;
import com.aloha.batch.batch.item.writer.BoardItemWriter;
import com.aloha.batch.dto.Board;


@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private BoardItemReader boardItemReader;

    @Autowired
    private BoardItemProcessor boardItemProcessor;

    @Autowired
    private BoardItemWriter boardItemWriter;

    // Job : BoardJob
    @Bean
    public Job scheduledBatchJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return jobBuilderFactory.get("BoardJob")
                .incrementer(new RunIdIncrementer())
                .start(step1(jobRepository, transactionManager))
                .next(step2(jobRepository, transactionManager))
                .preventRestart()
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return stepBuilderFactory.get("step1")
                .<Board, Board>chunk(1)
                .reader(boardItemReader)
                .processor(boardItemProcessor)
                .writer(boardItemWriter)
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return stepBuilderFactory.get("step2")
                .tasklet(step2Tasklet())
                .repository(jobRepository)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Tasklet step1Tasklet() {
        return (contribution, chunkContext) -> {
            // step1 내용
            System.out.println("Step 1 executed");
            return RepeatStatus.FINISHED;
        };
    }
    

    @Bean
    public Tasklet step2Tasklet() {
        BoardItemReader.index = 1;
        return (contribution, chunkContext) -> {
            // step2 내용
            System.out.println("Step 2 executed");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public JobBuilderFactory jobBuilderFactory(JobRepository jobRepository) {
        return new JobBuilderFactory(jobRepository);
    }

    @Bean
    public StepBuilderFactory stepBuilderFactory(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilderFactory(jobRepository, transactionManager);
    }

}