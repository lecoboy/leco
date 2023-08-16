package com.greg.leco.demo;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author greg
 * @version 2022/2/23
 */
public class XlgwyDemo {
    @Test
    public void pjhAndYt() {
        int pjhBuy = 60;
        int pjhSell = 300;
        int pjhNzTime = 2;
        int pjhShTime = 1;
        int num = pjhShTime / pjhNzTime;
        num += pjhShTime % pjhNzTime == 0 ? 0 : 1;
        System.out.println(num);
        int pjhSzTime = 11;
        int jdTime = 28;
        int pjhHaveTime = jdTime - (pjhSzTime - 1);
        int pjhNzCount = pjhHaveTime / pjhNzTime;
        int pjhTotalSell = pjhHaveTime / pjhNzTime * pjhSell;
        int pjhSellPerM = pjhTotalSell / pjhBuy;
        System.out.println(pjhSellPerM);

        int ytBuy = 400;
    }

    @Test
    public void test() {
//        System.out.println(needZzNum(11,1,2));
//        System.out.println(mlPerBucket(11,1,2,60,300));
        printInfo("pjh", 11,1,2,60,300);//960*44=42240
        printInfo("yt", 13,0,7,400,3000);
        printInfo("kfd", 10,2,1,0,150);
        // 5:4    x:100    10:4 x:100   x=10/4*100
        System.out.println("====");
        // 18..1(0)\2(16) 3(32)\4(16) 5(32)\6(16)
    }

    private void printInfo(String name, int szDay, int shDay, int nzDay, double buy,double sell) {
        System.out.println("==========");
        System.out.println(name);
        System.out.println(needZzNum(szDay, shDay, nzDay));
        if (shDay != 0 && shDay < nzDay) {
            System.out.print(">=");
        }
        System.out.print(mlPerBucket(szDay, shDay, nzDay, buy, sell) + "\n");
    }

    private BigDecimal mlPerBucket(int szDay, int shDay, int nzDay, double buy,double sell) {
        int jdDay = 28;
        int needZzNum = needZzNum(szDay, shDay, nzDay);
        int remainDay = jdDay - (szDay-1);
        int nzCount = remainDay / nzDay;
        if (buy == 0) {
            return new BigDecimal(sell+"").multiply(new BigDecimal(nzCount + ""));
        }
        BigDecimal totalSell = new BigDecimal(sell + "").multiply(new BigDecimal(nzCount + ""));
        BigDecimal totalBuy = new BigDecimal(buy + "").multiply(new BigDecimal(needZzNum + ""));

        return totalSell.subtract(totalBuy).divide(totalBuy, BigDecimal.ROUND_HALF_UP);
    }

    private int needZzNum(int szDay, int shDay, int nzDay) {
        int needZzNum;//
        int jdDay = 28;
//        int szDay;
        int remainDay = jdDay - (szDay-1);
//        int nzDay;//7
        int nzCount = remainDay / nzDay;//3

//        int shDay;//8
        if (shDay == 0) {
            return nzCount;
        }
        int thr = shDay / nzDay;
        thr += shDay % nzDay == 0 ? 0 : 1;
        needZzNum = Math.min(nzCount, thr);
        return needZzNum;
    }
}
