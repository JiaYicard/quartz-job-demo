package com.zzs.quartz.job;

/**
 * @author zzs
 * 2022/5/25 0025 15:37
 */
public class CustomJob {
    private String jobId;

    private String JobName;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }
}
