package com.gbwxy.demo.test;

import com.gbwxy.demo.config.MainConfigOfLifeCycle;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IOCTest_LifeCycle {

    @Test
    public void test01() {
        //1、创建ioc容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfLifeCycle.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        /**
         * 打印结果：
         * org.springframework.context.annotation.internalConfigurationAnnotationProcessor
         * org.springframework.context.annotation.internalAutowiredAnnotationProcessor
         * org.springframework.context.annotation.internalRequiredAnnotationProcessor
         * org.springframework.context.annotation.internalCommonAnnotationProcessor
         * org.springframework.context.event.internalEventListenerProcessor
         * org.springframework.context.event.internalEventListenerFactory
         * mainConfigOfLifeCycle
         * car
         */
//        for (String name : definitionNames) {
//            System.out.println(name);
//        }

        System.out.println("容器创建完成...");

        //关闭容器
        applicationContext.close();
    }

}
