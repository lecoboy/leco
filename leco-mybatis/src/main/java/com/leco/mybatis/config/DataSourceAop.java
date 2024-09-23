package com.leco.mybatis.config;

import com.leco.mybatis.context.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class DataSourceAop {

    @Pointcut("@annotation(com.leco.mybatis.annotation.Master)")
    public void masterPointcut() {

    }

    @Pointcut("@annotation(com.leco.mybatis.annotation.Slave)")
    public void slavePointcut() {

    }

    @Before("masterPointcut()")
    public void master() {
        DBContextHolder.master();
    }

    @Before("slavePointcut()")
    public void slave() {
        DBContextHolder.slave();
    }
}
