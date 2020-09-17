package com.chauncey.study.ohmysalary.entity;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName ABA
 * @Description TODO
 * @Author Chauncey
 * @Date 2020/9/8 下午6:26
 * @Version 1.0
 **/
@Slf4j
public class ABA {

    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    private static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        log.info("ABA问题===========");
        new Thread(() -> {
            log.info("当前线程：{} 修改成功与否： {}, 结果： {}",
                    Thread.currentThread().getName(),
                    atomicReference.compareAndSet(100,101),
                    atomicReference.get());
            log.info("当前线程：{} 修改成功与否： {}, 结果： {}",
                    Thread.currentThread().getName(),
                    atomicReference.compareAndSet(101,100),
                    atomicReference.get());
        },"T1").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
        log.info("ABA问题解决============");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info("当前线程: {} ,stamp {}",Thread.currentThread().getName(),stamp);
            log.info("当前线程：{} 修改成功与否： {}, 结果： {}, 版本号: {}",
                    Thread.currentThread().getName(),
                    atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1),
                    atomicStampedReference.getReference(),
                    atomicStampedReference.getStamp());
            log.info("当前线程：{} 修改成功与否： {}, 结果： {}, 版本号: {}",
                    Thread.currentThread().getName(),
                    atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1),
                    atomicStampedReference.getReference(),
                    atomicStampedReference.getStamp());
        },"t3").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            log.info("当前线程: {} ,stamp {}",Thread.currentThread().getName(),stamp);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("当前线程：{} 修改成功与否： {}, 结果： {}, 版本号: {}",
                    Thread.currentThread().getName(),
                    atomicStampedReference.compareAndSet(100,101,stamp,stamp+1),
                    atomicStampedReference.getReference(),
                    stamp);
        },"t4").start();


    }
}
