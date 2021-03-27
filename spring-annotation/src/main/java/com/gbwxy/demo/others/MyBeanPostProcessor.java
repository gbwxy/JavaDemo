package com.gbwxy.demo.others;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入到容器中
 *
 * @author lfy
 */

/**
 * 打印顺序：
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
