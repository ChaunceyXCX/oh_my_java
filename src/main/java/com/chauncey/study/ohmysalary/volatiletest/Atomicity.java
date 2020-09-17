package com.chauncey.study.ohmysalary.volatiletest;

import com.chauncey.study.ohmysalary.entity.TestObj;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName Atomicity
 * @Description 不可原子性验证
 * @Author Chauncey
 * @Date 2020/9/7 下午6:04
 * @Version 1.0
 **/
@Slf4j
public class Atomicity {
    public static void main(String[] args) {
        TestObj testObj = new TestObj();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    //不保证原子性，导致并发条件下共同操作变量后结果覆盖
                    testObj.numberPlusPlus();
                    //atomic 以来CAS锁保证原子性
                    testObj.atomicIntegerPlusPlus();
                }
            },"1").start();
        }

        while (Thread.activeCount()>2) {
            Thread.yield();
        }

        log.info("number：{}" ,testObj.getNumber() );
        log.info("numberV：{}" ,testObj.getNumberV() );
        log.info("atomic：{}" ,testObj.getAtomicInteger() );

    }
}
