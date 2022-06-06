package com.zzs.quartz.job;

import org.quartz.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 简单定时器任务
 * @author zzs
 * 2022/5/25 0025 16:18
 */
@Component
public class SimpleTriggerJob {


//    @PostConstruct
    void initJob() throws SchedulerException, ParseException {
        SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler = schedFact.getScheduler();
        scheduler.start();

        //定义自己的工作类，JobImpl实现Job接口
        JobDetail myJob = newJob(JobImpl.class)
                .withIdentity("myJob", "group1")
                .build();

        //设置执行触发器，每5秒执行一次
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();


        Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-05-26 17:43:00");
        //特定时间触发器(jobName要和job的一致)
        SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .forJob("myJob", "group1")
                .build();

        //特定时间触发，然后每隔5秒触发一次,重复10+1次
        trigger = newTrigger()
                .withIdentity("trigger3", "group1")
                // if a start time is not given (if this line were omitted), "now" is implied
                .startAt(startTime)
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(5)
                        // note that 10 repeats will give a total of 11 firings
                        .withRepeatCount(10))
                // identify job with handle to its JobDetail itself
                .forJob(myJob)
                .build();

        //设置自己的job和触发器
        scheduler.scheduleJob(myJob, trigger);

        //其它简单例子参考
        //http://www.quartz-scheduler.org/documentation/2.4.0-SNAPSHOT/tutorials/tutorial-lesson-05.html
    }

}
