package com.gbwxy.B.SwordForOffer;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2021/4/8
 */

/**
 * 双重检验锁模式-线程安全
 */
public class A_02_Singleton {

    //volatile可以使instance变量不会在多线程中存在副本，直接从内存中读
    private volatile static A_02_Singleton INSTANCE;

    //即：volatile的赋值操作后面会有个“内存屏障”，防止读操作被JVM重排序到内存屏障之前。
    private A_02_Singleton() {
    }

    public static A_02_Singleton getInstance() {
        if (INSTANCE == null) {
            synchronized (A_02_Singleton.class) {
                if (INSTANCE == null) {
                    return new A_02_Singleton();
                }
            }
        }
        return INSTANCE;
    }

}
