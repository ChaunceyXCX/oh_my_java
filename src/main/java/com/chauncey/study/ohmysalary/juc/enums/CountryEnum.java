package com.chauncey.study.ohmysalary.juc.enums;

import lombok.Getter;

/**
 * @ClassName CountryEnum
 * @Description TODO
 * @Author Chauncey
 * @Date 2020/9/17 下午6:25
 * @Version 1.0
 **/
public enum  CountryEnum {
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"韩"),
    FIVE(5,"赵"),
    SIX(6,"魏"),
    SEVEN(7,"秦");

    @Getter private Integer code;
    @Getter private String msg;

    CountryEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CountryEnum forEachCountry(int index) {
        for (CountryEnum element :
                CountryEnum.values()) {
            if (index == element.getCode()) {
                return element;
            }
        }
        return null;
    }
}
