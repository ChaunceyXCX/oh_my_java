package com.chauncey.study.ohmysalary.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ReentrantLockDemo
 * @Description 可重入锁代码验证
 * @Author Chauncey
 * @Date 2020/9/16 下午4:03
 * @Version 1.0
 **/
class Test implements Runnable {
    public synchronized void send() {
        System.out.println(Thread.currentThread().getName() + "\t send");
        receive();
    }

    public synchronized void receive() {
        System.out.println(Thread.currentThread().getName() + "\t ########receive");
    }

    Lock lock = new ReentrantLock(); //非公平锁(unfair)
    Lock lock1 = new ReentrantLock(true); //公平锁(fair)

    public void send1() {
        lock.lock();
        lock.lock(); //可以多次加锁但要对应多次解锁

        System.out.println(Thread.currentThread().getName() + "\t @@send1");
        receive1();
        lock.unlock();
        lock.unlock();
    }

    public void receive1() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "\t @@########receive1");

        lock.unlock();
    }

    @Override
    public void run() {
        send1();
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Test test = new Test();

        new Thread(() -> {
            test.send();
        }, "t1").start();

        new Thread(() -> {
            test.send();
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        Thread t3 = new Thread(test);
        Thread t4 = new Thread(test);

        t3.start();
        t4.start();
    }
}
