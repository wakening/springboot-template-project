package com.example.provider.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时日志，确认存活
 *
 * @author wakening
 */
@Configuration
@EnableScheduling
public class ScheduleRunner {

    private final Logger logger = LoggerFactory.getLogger(ScheduleRunner.class);

    /**
     * 每几分钟跑一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void doSchedule() {
        logger.info("application heartbeat: " + System.currentTimeMillis());
    }

}
