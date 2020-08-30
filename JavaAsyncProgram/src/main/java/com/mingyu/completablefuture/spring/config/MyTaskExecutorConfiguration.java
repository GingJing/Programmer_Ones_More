package com.mingyu.completablefuture.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * TaskExecutor配置类
 *
 * @author: GingJingDM
 * @date: 2020年 08月29日 21时13分
 * @version: 1.0
 */
@Configuration
public class MyTaskExecutorConfiguration {

    @Bean("myTaskExecutor")
    public ThreadPoolTaskExecutor myTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(10);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("testTaskPoolExecutor");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }
}
