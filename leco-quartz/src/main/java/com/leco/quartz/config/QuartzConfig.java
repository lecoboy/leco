package com.leco.quartz.config;

import com.leco.quartz.job.DemoJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail demoJobDetail() {
        //指定任务描述具体的实现类
        return JobBuilder.newJob(DemoJob.class)
                // 指定任务的名称
                .withIdentity("demoJob")
                // 任务描述
                .withDescription("任务描述：用于测试quartz job")
                // 每次任务执行后进行存储
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger demoJobTrigger() {
        //创建触发器
        return TriggerBuilder.newTrigger()
                // 绑定工作任务
                .forJob(demoJobDetail())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule())
                // 悉尼时区，每天2点执行
                //.withSchedule(CronScheduleBuilder.cronSchedule("0 00 02 * * ?")
                //        .inTimeZone(TimeZone.getTimeZone("Australia/Sydney")))
                .build();
    }
}