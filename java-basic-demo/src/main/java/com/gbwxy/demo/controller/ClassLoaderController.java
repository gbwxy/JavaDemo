package com.gbwxy.demo.controller;

import com.gbwxy.demo.entity.Robot;
import com.gbwxy.demo.utils.MyClassLoader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "ClassLoaderController", tags = "类加载器与类加载过程")
@RestController
public class ClassLoaderController extends BaseController {

    @ApiOperation("类加载器")
    @GetMapping("/classloader/test")
    public void classLoader() {
        try {
            MyClassLoader myClassLoader = new MyClassLoader("E:\\github\\JavaBasicDemo\\", "WaliClassLoader");
            Class a = myClassLoader.loadClass("Wali");
            System.out.println(a.getClassLoader());
            a.newInstance();
            System.out.println(a.getClassLoader().getParent());
            System.out.println(a.getClassLoader().getParent().getParent());
            System.out.println(a.getClassLoader().getParent().getParent().getParent());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @ApiOperation("loadCLass和forName")
    @GetMapping("/classloader/diff")
    public void LoadClassAndForName() {
        try {
//            MyClassLoader myClassLoader = new MyClassLoader("E:\\github\\JavaBasicDemo\\", "WaliClassLoader");
//            Class a = myClassLoader.loadClass("Wali");
//            System.out.println("------------------------------------------");
//
//            Class rc = Class.forName("com.example.demo.basic.entity.Robot");
//
//            System.out.println("------------------------------------------");
//
//            System.out.println(a.getClassLoader());
//            a.newInstance();
//
//            System.out.println("------------------------------------------");
//
//            rc.newInstance();

            ClassLoader classLoader = Robot.class.getClassLoader();
            classLoader.loadClass("Robot");

            Class.forName("com.gbwxy.demo.entity.Robot",false,classLoader);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
