package com.gbwxy.demo.service;

import com.gbwxy.demo.entity.Robot;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

@Service
public class InvokeService {

    public void invokeTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        //֪ʶ�㣺
        //1.forName0��������native���Σ���native����
        //2.ClassLoader �������������Զ��������
        Class rc = Class.forName("com.gbwxy.demo.entity.Robot");
        Robot r = (Robot) rc.newInstance();
        System.out.println("Class name is :" + rc.getName());

        Method getHello = rc.getDeclaredMethod("sayHello", String.class);
        getHello.setAccessible(true);
        Object str = getHello.invoke(r, "bom");
        System.out.println(str.toString());

        Method getHi = rc.getMethod("sayHi",String.class);
        getHi.invoke(r,"Welcome");

        Field name = rc.getDeclaredField("name");
        name.setAccessible(true);
        name.set(r,"Arike");

        getHi.invoke(r,"Welcome");


    }


    /**
     * ���￼������֪ʶ��
     * 1.Integer -128��127,֮���л���
     * 2.�Զ�װ��Ͳ���
     * 3.ͨ������ȥ�޸� private final����
     * @param i1
     * @param i2
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public  void swap(Integer i1,Integer i2) throws NoSuchFieldException, IllegalAccessException {
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);


        //�½�һ���ڴ�ռ�
        //int tmp = i1.intValue(); ֱ�Ӳ���������У���Ϊtmp��i1ָ����ڴ�ռ�
        //���ֱ�Ӳ��䣬�����޸�i1��value����tmpҲ�����޸���
        Integer tmp = new Integer(i1.intValue());

        value.set(i1,i2.intValue()); // Integer.valueOf(i2.intValue()).intValue()
        value.set(i2,tmp);



    }

}
