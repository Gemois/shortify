package com.gmoi.shortify.configuration;

import com.gmoi.shortify.properties.UrlProperties;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@AllArgsConstructor
public class SchedulingConfig {

    private final UrlProperties urlProperties;

    @Bean()
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(urlProperties.getScheduler().getThreadPoolSize());
        taskScheduler.setThreadNamePrefix("UrlCleanupScheduler-");
        return taskScheduler;
    }
}
