package com.chauncey.study.ohmysalary.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TestObj
 * @Description TODO
 * @Author Chauncey
 * @Date 2020/9/7 下午6:07
 * @Version 1.0
 **/
@Data
@NoArgsConstructor
public
class TestObj{
    private int number;
    private volatile int numberV;

    private AtomicInteger atomicInteger = new AtomicInteger();

    public TestObj(int number, int numberV) {
        this.number = number;
        this.numberV = numberV;
    }

    public void numberPlusPlus() {
        numberV++;
        number++;
    }

    public void atomicIntegerPlusPlus() {
        atomicInteger.getAndIncrement();
    }
}
