package com.zzs.quartz.job;

import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;


/**
 * cron表达式任务
 *
 * @author zzs
 * 2022/5/30  16:10
 */
@Component
public class CronTriggerJob {
    @PostConstruct
    void initJob() throws SchedulerException, ParseException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFact.getScheduler();
        scheduler.start();

        //定义自己的工作类，JobImpl实现Job接口
        JobDetail myJob = JobBuilder.newJob(JobImpl.class)
                .withIdentity("myJob", "group1")
                .build();

        //构建一个触发器，该触发器将在每天上午 8 点到下午 5 点之间每隔一分钟触发一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?");

        //每隔10秒钟触发一次
        CronScheduleBuilder cronScheduleBuilder1 = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

        //构建一个每天上午 10:42 触发的触发器
        CronScheduleBuilder cronScheduleBuilder2 = CronScheduleBuilder.dailyAtHourAndMinute(10, 42);

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger3", "group1")
                .withSchedule(cronScheduleBuilder1)
                .forJob(myJob)
                .build();
        scheduler.scheduleJob(myJob, cronTrigger);
    }
}
