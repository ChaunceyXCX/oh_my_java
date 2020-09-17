package com.chauncey.study.ohmysalary.volatiletest;

import com.chauncey.study.ohmysalary.entity.TestObj;
import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Visibility
 * @Description 可见性验证
 * @Author Chauncey
 * @Date 2020/9/7 下午4:03
 * @Version 1.0
 **/
public class Visibility {
    public static void main(String[] args) {
        TestObj testObj = new TestObj(10,10);
        System.out.println("当前线程： "
                + Thread.currentThread().getName()
                + "当前属性值： " +testObj.getNumber()+"--"+testObj.getNumberV());

        new Thread(() -> {
            System.out.println("volatileT come in...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testObj.setNumberV(100);
            System.out.println("当前线程： "
                    + Thread.currentThread().getName()
                    + "当前属性值： " +testObj.getNumber()+"--"+testObj.getNumberV());
            System.out.println("volatileT out... ");
        },"volatileT").start();


        while (testObj.getNumberV()==10) {

        }

        System.out.println("当前线程： "
                + Thread.currentThread().getName()
                + "当前属性值： " +testObj.getNumber()+"--"+testObj.getNumberV());

        new Thread(() -> {
            System.out.println("T come in...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testObj.setNumber(100);
            System.out.println("当前线程： "
                    + Thread.currentThread().getName()
                    + "当前属性值： " +testObj.getNumber()+"--"+testObj.getNumberV());
            System.out.println("T out...");
        },"T").start();

        //number 没有加volatile 所以在此会死循环
        while (testObj.getNumber()==10) {

        }

        System.out.println("当前线程： "
                + Thread.currentThread().getName()
                + "当前属性值： " +testObj.getNumber()+"--"+testObj.getNumberV());
    }
}
