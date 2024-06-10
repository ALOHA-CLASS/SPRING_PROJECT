package com.aloha.batch.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class BatchScheduler {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importUserJob;

    /**
     * 현재 타임스탬프를 매개변수로 하여 배치 작업을 실행합니다.
     */
    // @Scheduled(cron = "0 */5 * * * ?") // 매 5분마다 실행
    @Scheduled(cron = "0 * * * * ?") // 매 분 0초에 실행
    public void runBatchJob() {
        try {
            // 현재 타임스탬프로 작업 매개변수를 빌드합니다.
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis())
                    .toJobParameters();

            // 구성된 매개변수로 작업을 실행합니다.
            jobLauncher.run(importUserJob, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}