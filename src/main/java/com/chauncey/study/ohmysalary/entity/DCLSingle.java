package com.chauncey.study.ohmysalary.entity;

/**
 * @ClassName DCLSingle
 * @Description 双端检锁单例（高并发单例）
 * @Author Chauncey
 * @Date 2020/9/8 下午4:08
 * @Version 1.0
 **/
public class DCLSingle {
    private static volatile DCLSingle instance; //双端检锁加volatile禁重排

    public DCLSingle() {
        System.out.println(Thread.currentThread().getName()+"创建对象"); //将只看到控制台打印一次
    }

    //双端检锁实现
    public static DCLSingle getInstance(){
        if (instance == null) {
            synchronized (DCLSingle.class) {
                if (instance == null) {
                    instance = new DCLSingle();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10 ; i++) {
            new Thread(()->{
                DCLSingle.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
