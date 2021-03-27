package com.gbwxy.demo.service;

import com.gbwxy.demo.entity.Robot;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

@Service
public class InvokeService {

    public void invokeTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        //知识点：
        //1.forName0函数，被native修饰，是native方案
        //2.ClassLoader 加载器，可以自定义加载器
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
     * 这里考到三个知识点
     * 1.Integer -128到127,之间有缓存
     * 2.自动装箱和拆箱
     * 3.通过反射去修改 private final变量
     * @param i1
     * @param i2
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public  void swap(Integer i1,Integer i2) throws NoSuchFieldException, IllegalAccessException {
        Field value = Integer.class.getDeclaredField("value");
        value.setAccessible(true);


        //新建一块内存空间
        //int tmp = i1.intValue(); 直接拆箱操作不行，因为tmp是i1指向的内存空间
        //如果直接拆箱，后续修改i1的value，则tmp也跟着修改了
        Integer tmp = new Integer(i1.intValue());

        value.set(i1,i2.intValue()); // Integer.valueOf(i2.intValue()).intValue()
        value.set(i2,tmp);



    }

}
