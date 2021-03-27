package com.gbwxy.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


/**
 * ִ��˳��
 * car constructor...
 * postProcessBeforeInitialization...car=>com.gbwxy.bean.Car@28261e8e
 * car....@PostConstruct...
 * cat...implements InitializingBean - afterPropertiesSet...
 * car ...initMethod...
 * postProcessAfterInitialization...car=>com.gbwxy.bean.Car@28261e8e
 *
 *
 * �����������...
 * car....@PreDestroy...
 * cat...implements DisposableBean - destroy...
 * car ... destroyMethod...
 *
 */

@Component
public class Car implements InitializingBean, DisposableBean {

    public Car() {
        System.out.println("car constructor...");
    }

    public void init() {
        System.out.println("car ...initMethod...");
    }

    public void detory() {
        System.out.println("car ... destroyMethod...");
    }

    //���󴴽�����ֵ֮�����
    @PostConstruct
    public void initJava() {
        System.out.println("car....@PostConstruct...");
    }

    //�����Ƴ�����֮ǰ
    @PreDestroy
    public void detoryJava() {
        System.out.println("car....@PreDestroy...");
    }

    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat...implements DisposableBean - destroy...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat...implements InitializingBean - afterPropertiesSet...");
    }
}
