package com.leco.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class DemoJob  extends AbstractRetryableJob {
    @Override
    public String doExecute() throws Exception {
        setMaxRetry(3);
        setInterval(3000);
        log.info("doing task...");
        int i = 1 / 10;
        if (i == 0) {
            throw new JobExecutionException("demo job failed");
            //return "failed";
        }
        log.info("task done");
        return "success";
    }

    //@Override
    //public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    //    log.info("do something...");
    //}
}
