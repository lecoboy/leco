package com.greg.leco.demo;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author greg
 * @version 2024/8/11
 **/
public class TimeZoneDemo {
    public static void main(String[] args) throws Exception {
        test7();
    }

    /**
     * 测试 LocalTime.now(zoneId) 是不是显示当地时分秒
     * @throws Exception
     */
    public static void test7() throws Exception {
        // Australia/Melbourne
        String timezone = "Australia/Melbourne";
        ZoneId zoneId = ZoneId.of(timezone);
        System.out.println(LocalTime.now(zoneId));
    }

    public static void test6() throws Exception {
        // 当地的cutoff是3点，不应该用with，而是用当天0点加3个小时
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ZoneId utc = ZoneId.of("UTC");
        ZoneId syd = ZoneId.of("Australia/Sydney");
        ZoneId systemZone = ZoneId.systemDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse("2024-04-06 16:00:00");
        System.out.println(date.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), systemZone);
        System.out.println(localDateTime.atZone(systemZone).toEpochSecond());
        System.out.println("===先根据date创建系统时区时间，再转syd时区");
        System.out.println(localDateTime.atZone(systemZone).withZoneSameInstant(syd).toLocalDateTime());
        System.out.println(localDateTime.atZone(systemZone).withZoneSameInstant(syd).toEpochSecond());
        System.out.println("---根据date直接创建syd时区");
        System.out.println(LocalDateTime.ofInstant(date.toInstant(), syd));
        System.out.println(LocalDateTime.ofInstant(date.toInstant(), syd).atZone(syd).toEpochSecond());
    }

    public static void test5() throws Exception {
        // 当地的cutoff是3点，不应该用with，而是用当天0点加3个小时
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ZoneId utc = ZoneId.of("UTC");
        ZoneId syd = ZoneId.of("Australia/Sydney");
        // 悉尼在当地时间 2024年04月07日，03:00:00 时钟向后调整 1 小时 变为 2024年04月07日，02:00:00，结束夏令时
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse("2024-04-06 16:00:00");
        System.out.println("===传入时间");
        System.out.println(date.toInstant());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Australia/Sydney"));
        LocalDateTime l2 = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Australia/Sydney"));
        System.out.println(localDateTime);
        System.out.println(localDateTime.atZone(syd).getOffset());
        l2 = l2.with(LocalTime.of(0, 0, 0));
        l2 = l2.plusHours(3);
        System.out.println(localDateTime.atZone(syd));
        System.out.println(l2.atZone(syd));
        //l2 = localDateTime.minusSeconds(1);
        localDateTime = localDateTime.atZone(syd).withZoneSameInstant(utc).toLocalDateTime();
        System.out.println(localDateTime);
        System.out.println(l2.atZone(syd).withZoneSameInstant(utc).toLocalDateTime());

    }

    public static void test4() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ZoneId utc = ZoneId.of("UTC");
        ZoneId syd = ZoneId.of("Australia/Sydney");
        // 悉尼在当地时间 2024年04月07日，03:00:00 时钟向后调整 1 小时 变为 2024年04月07日，02:00:00，结束夏令时
        // with3点的offset多少？再此基础减1秒后offset是多少呢？
        LocalDateTime localDateTime = LocalDateTime.of(2024, 4, 7, 1, 0, 0);
        System.out.println(localDateTime.atZone(syd).getOffset());
        localDateTime = localDateTime.with(LocalTime.of(3, 0, 0));
        System.out.println(localDateTime.atZone(syd).getOffset());
        localDateTime = localDateTime.minusSeconds(1);
        System.out.println(localDateTime.atZone(syd).getOffset());
    }

    public static void test3() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ZoneId utc = ZoneId.of("UTC");
        ZoneId syd = ZoneId.of("Australia/Sydney");
        // 时间戳转时间再转时间戳，值发生改变
        long ts = 1712422799;
        System.out.println(ts);
        LocalDateTime localDateTime = Instant.ofEpochSecond(ts).atZone(syd).toLocalDateTime();
        System.out.println(localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli());
    }

    public static void test2() throws Exception {
        // 悉尼在当地时间 2024年04月07日，03:00:00 时钟向后调整 1 小时 变为 2024年04月07日，02:00:00，结束夏令时
        // 那么给一个时间，2024年04月07日，01:00:00，然后with3:00:00，这时是不是夏令时？
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ZoneId utc = ZoneId.of("UTC");
        ZoneId syd = ZoneId.of("Australia/Sydney");
        LocalDateTime localDateTime = LocalDateTime.of(2024, 4, 7, 1, 0, 0);
        boolean flag = syd.getRules().isDaylightSavings(localDateTime.atZone(syd).toInstant());
        System.out.println("===1点是夏令时吗？");
        System.out.println(flag);
        System.out.println(localDateTime.atZone(syd).toEpochSecond());
        localDateTime = localDateTime.with(LocalTime.of(3, 0, 0));
        System.out.println("===with3点是夏令时吗？");
        System.out.println(localDateTime.atZone(syd).getOffset());
        System.out.println(syd.getRules().isDaylightSavings(localDateTime.atZone(syd).toInstant()));
        System.out.println(localDateTime.atZone(syd).toEpochSecond());
        //localDateTime = localDateTime.atZone(syd).minusSeconds(1).withZoneSameInstant(syd).toLocalDateTime();
        //localDateTime = localDateTime.minusSeconds(1);
        long ts = localDateTime.atZone(syd).toEpochSecond() - 1;
        System.out.println("ts================");
        System.out.println(ts);
        System.out.println(Instant.ofEpochSecond(ts));
        System.out.println(Instant.ofEpochSecond(ts).atZone(utc));
        System.out.println(Instant.ofEpochSecond(ts).atZone(syd));
        System.out.println("ENDts===============");
        //localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(ts), syd);
        //localDateTime = Instant.ofEpochSecond(ts).atZone(utc).toLocalDateTime();
        localDateTime = Instant.ofEpochSecond(ts).atZone(utc).withZoneSameInstant(syd).toLocalDateTime();
        System.out.println(localDateTime);
        System.out.println("===3点减一秒是夏令时吗？");
        System.out.println(syd.getRules().isDaylightSavings(localDateTime.atZone(syd).toInstant()));
        System.out.println(localDateTime.atZone(ZoneId.of("UTC+10")).toEpochSecond());
        //localDateTime = localDateTime.atZone(utc).withZoneSameInstant(syd).toLocalDateTime();
        localDateTime = ZonedDateTime.of(localDateTime, utc).withZoneSameInstant(syd).toLocalDateTime();
        System.out.println(localDateTime);
        System.out.println(localDateTime.atZone(syd).toEpochSecond());
    }

    public static void test1() throws Exception {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        ZoneId utc = ZoneId.of("UTC");
        ZoneId syd = ZoneId.of("Australia/Sydney");
        // 悉尼在当地时间 2024年04月07日，03:00:00 时钟向后调整 1 小时 变为 2024年04月07日，02:00:00，结束夏令时
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse("2024-04-06 16:00:00");
        System.out.println("===传入时间");
        System.out.println(date.toInstant());
        Date date1 = sdf.parse("2024-04-05 16:00:00");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("Australia/Sydney"));
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.of("Australia/Sydney"));
        //System.out.println(localDateTime);
        //System.out.println(localDateTime1);
        localDateTime = localDateTime.with(LocalTime.of(3, 0, 0));
        System.out.println("===当地3点");
        System.out.println(localDateTime);
        localDateTime = localDateTime.minusSeconds(1);
        System.out.println("===减一秒");
        System.out.println(localDateTime);
        System.out.println("===减一秒后是夏令时吗？");
        System.out.println(syd.getRules().isDaylightSavings(localDateTime.atZone(syd).toInstant()));
        Date ret = Date.from(localDateTime.atZone(syd).toInstant());
        System.out.println("===转回utc");
        System.out.println(ret.toInstant());
        System.out.println("===转回utc2");
        LocalDateTime localDateTime2 = localDateTime.atZone(syd).withZoneSameInstant(utc).toLocalDateTime();
        System.out.println(localDateTime2);
    }
}
