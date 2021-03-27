package com.gbwxy.demo.config;

import com.gbwxy.demo.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.*;

@ComponentScans(
        value = {
                @ComponentScan("com.gbwxy.demo.bean"),
                @ComponentScan("com.gbwxy.demo.others")
        }
)
@Configuration
public class MainConfigOfLifeCycle {

    @Bean(initMethod = "init", destroyMethod = "detory")
    public Car car() {
        return new Car();
    }

}
