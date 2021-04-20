package com.gbwxy.B.SwordForOffer;

/**
 * ������
 *
 * @Author wangjun
 * @Date 2021/4/8
 */

/**
 * ˫�ؼ�����ģʽ-�̰߳�ȫ
 */
public class A_02_Singleton {

    //volatile����ʹinstance���������ڶ��߳��д��ڸ�����ֱ�Ӵ��ڴ��ж�
    private volatile static A_02_Singleton INSTANCE;

    //����volatile�ĸ�ֵ����������и����ڴ����ϡ�����ֹ��������JVM�������ڴ�����֮ǰ��
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
