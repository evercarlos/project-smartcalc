package com.tec.smartcalc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Value("${tec.async.thread.corePoolSize}")
    int corePoolSize;

    @Value("${tec.async.thread.maxPoolSize}")
    int maxPoolSize;

    @Value("${tec.async.thread.queueCapacity}")
    int queueCapacity;

    @Bean(name = "taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);  // min threads
        executor.setMaxPoolSize(maxPoolSize);  // max threads
        executor.setQueueCapacity(queueCapacity); // queue
        executor.setThreadNamePrefix("async-executor-calculator-");
        executor.initialize();
        return executor;
    }
}