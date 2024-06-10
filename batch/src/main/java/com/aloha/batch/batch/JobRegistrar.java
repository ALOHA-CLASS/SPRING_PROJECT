package com.aloha.batch.batch;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.DuplicateJobException;
import org.springframework.batch.core.configuration.JobFactory;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobRegistrar implements JobRegistry {

    @Autowired
    private JobRegistry jobRegistry;

    List<String> jobNames = Arrays.asList("BoardJob3");

    @Override
    public Collection<String> getJobNames() {
        return jobNames;
    }

    @Override
    public Job getJob(String name) throws NoSuchJobException {
        return jobRegistry.getJob(name);
    }

    @Override
    public void register(JobFactory jobFactory) throws DuplicateJobException {
        jobRegistry.register(jobFactory);
    }

    @Override
    public void unregister(String jobName) {
        jobRegistry.unregister(jobName);
    }
}