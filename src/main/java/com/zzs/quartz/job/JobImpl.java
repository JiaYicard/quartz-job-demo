package com.zzs.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zzs
 * 2022/5/25 0025 15:50
 */
public class JobImpl implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("定时器触发了");
        System.out.println("jobExecutionContext = " + jobExecutionContext);
    }
}
