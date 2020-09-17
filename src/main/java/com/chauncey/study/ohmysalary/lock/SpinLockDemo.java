package com.chauncey.study.ohmysalary.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SpinLockDemo
 * @Description 自旋锁Demo
 * @Author Chauncey
 * @Date 2020/9/17 上午11:29
 * @Version 1.0
 **/
public class SpinLockDemo {

    private AtomicReference<Thread> atomicReference = new AtomicReference<>(); //初始化时没有值就为null

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"\t come in...");
        while (!atomicReference.compareAndSet(null,thread)) {

            System.out.println(Thread.currentThread().getName()+"\t 锁被TA占用获取失败...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(thread.getName()+"\t 得到锁");
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"\t 释放锁");
    }



    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"TA").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"TB").start();
    }
}
