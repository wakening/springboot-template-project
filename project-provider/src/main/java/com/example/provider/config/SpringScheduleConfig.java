package com.example.provider.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * springboot简单定时任务配置。
 * tips: 复杂分布式调度请用quartz、xxl等
 *
 * @author wakening
 */
@Slf4j
@Configuration
public class SpringScheduleConfig implements SchedulingConfigurer {

    /**
     * 修改默认线程池
     *
     * @param scheduledTaskRegistrar ScheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        final int corePoolSize = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(corePoolSize,
                new BasicThreadFactory.Builder().namingPattern("spring-schedule-pool-%d").daemon(true).build());
        scheduledTaskRegistrar.setScheduler(executorService);
    }

    /**
     * 定时日志，确认存活。
     * 每几分钟跑一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void heartbeatPrint() {
        log.info("application heartbeat: {}", System.currentTimeMillis());
    }

}
