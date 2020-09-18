package com.chauncey.study.ohmysalary.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReadWriteLockDemo
 * @Description 读写锁demo
 * @Author Chauncey
 * @Date 2020/9/17 下午2:49
 * @Version 1.0
 **/
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void get(String key) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 开始读取： "+key);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object obj = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t 读取完毕： "+key+"<==>" +obj);
        lock.readLock().unlock();
    }

    public void put(String key,Object val) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t 开始写入： "+key+"<==>" +val);
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(key, val);
        System.out.println(Thread.currentThread().getName()+"\t 开始写入： "+key+"<==>" +val);
        lock.writeLock().unlock();
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            String s = ""+i;
            new Thread(() -> {
                myCache.put(s,s);
            },s).start();
        }

        for (int i = 0; i < 5; i++) {
            String s = ""+i;
            new Thread(() -> {
                myCache.get(s);
            },s).start();
        }
    }
}
