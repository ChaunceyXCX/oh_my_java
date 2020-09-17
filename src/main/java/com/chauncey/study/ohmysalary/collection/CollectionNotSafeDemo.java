package com.chauncey.study.ohmysalary.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName CollectionNotSafeDemo
 * @Description Java容器线程安全问题
 * @Author Chauncey
 * @Date 2020/9/9 下午5:53
 * @Version 1.0
 **/
@Slf4j
public class CollectionNotSafeDemo {

    //java.util.ConcurrentModificationException 并发修改异常
    /**
     * 解决方案
     * 1. new Vector<>();
     * 2. Collections.synchronizedList(new ArrayList<>());
     * 3. new CopyOnWriteArrayList<>();
     */
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                log.info(list.toString());
            },String.valueOf(i)).start();
        }
    }


}
