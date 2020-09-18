package com.chauncey.study.ohmysalary.juc;

import com.chauncey.study.ohmysalary.juc.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchDemo
 * @Description 到计数门闩
 * @Author Chauncey
 * @Date 2020/9/17 下午6:23
 * @Version 1.0
 **/
public class CountDownLatchDemo {

    private static final int countrySize = 6;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(countrySize);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 国灭");
                countDownLatch.countDown();
            }, CountryEnum.forEachCountry(i).getMsg()).start();
        }

        countDownLatch.await();
        System.out.println("秦国一统江山");
    }
}
