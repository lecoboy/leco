package com.leco.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
public abstract class AbstractRetryableJob implements Job {
    public abstract String doExecute() throws Exception;
    private int maxRetry = 3;
    private long interval = 1000 * 60 * 5;
    
    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }
    public void setInterval(long interval) {
        this.interval = interval;
    }
    private String generateRetryJobName(String jobName, int retryCount) {
        int pos = jobName.indexOf("#");
        if (pos >= 0) {
            return jobName.substring(0, pos + 1) + retryCount;
        } else {
            return jobName + "#" + retryCount;
        }
    }
    private void retry(JobExecutionContext context) {
        JobDataMap jodData = context.getJobDetail().getJobDataMap();
        JobKey jobKey = context.getJobDetail().getKey();
        String retryStr = (String) jodData.get("retry");
        int retryCount = 0;
        if (StringUtils.hasText(retryStr)) {
            retryCount = Integer.parseInt(retryStr);
        }
        if (retryCount >= maxRetry) {
            log.error("job {} retry reach max {}", jobKey, maxRetry);
        } else {
            retryCount += 1;
            log.warn("job {} will retry after {} s", jobKey, interval * retryCount / 1000);
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("retry", retryCount + "");

            JobDetail jobDetail = JobBuilder
                    .newJob(this.getClass())
                    .withIdentity(generateRetryJobName(jobKey.getName(), retryCount), jobKey.getGroup())
                    .usingJobData(jobDataMap)
                    .build();
            Trigger trigger = TriggerBuilder
                    .newTrigger()
                    .startAt(new Date(System.currentTimeMillis() + interval * retryCount))
                    .build();
            try {
                context.getScheduler().scheduleJob(jobDetail, trigger);
            } catch (SchedulerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            String result = doExecute();
            if (!StringUtils.isEmpty(result) && result.equals("failed")) {
                retry(context);
            }
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            retry(context);
        }
    }
}
