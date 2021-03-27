package com.gbwxy.demo.others;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ���ô���������ʼ��ǰ����д�����
 * �����ô��������뵽������
 *
 * @author lfy
 */

/**
 * ��ӡ˳��
 * car constructor...
 * postProcessBeforeInitialization...car=>com.gbwxy.demo.bean.Car@28261e8e
 * car....@PostConstruct...
 * cat...implements InitializingBean - afterPropertiesSet...
 * car ...initMethod...
 * postProcessAfterInitialization...car=>com.gbwxy.demo.bean.Car@28261e8e
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("postProcessBeforeInitialization..." + beanName + "=>" + bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // TODO Auto-generated method stub
        System.out.println("postProcessAfterInitialization..." + beanName + "=>" + bean);
        return bean;
    }

}
