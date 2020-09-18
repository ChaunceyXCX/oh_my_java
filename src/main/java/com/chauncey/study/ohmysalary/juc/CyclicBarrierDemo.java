package com.chauncey.study.ohmysalary.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierDemo
 * @Description 可循环屏障
 * @Author ChaunceyA
 * @Date 2020/9/18 上午10:24
 * @Version 1.0
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,new Thread(() ->{
            System.out.println("集齐七龙珠，召唤神龙");
        }));

        for (int i = 0; i < 7; i++) {
            final String temp = i+"";
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t发现龙珠"+"\t" +temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
