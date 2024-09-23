package com.leco.mybatis.context;

import com.leco.mybatis.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    //private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("switch to master");
    }

    public static void slave() {
        set(DBTypeEnum.SLAVE1);
        log.info("switch to slave1");
    }

}
