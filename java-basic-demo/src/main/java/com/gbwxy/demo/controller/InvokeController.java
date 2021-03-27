package com.gbwxy.demo.controller;

import com.gbwxy.demo.service.InvokeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "InvokeController", tags = "反射")
@RestController
public class InvokeController extends BaseController {

    @Resource
    private InvokeService invokeService;

    @ApiOperation("反射测试")
    @GetMapping("/invoke/test")
    public void invokeTest() {
        try {
            invokeService.invokeTest();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @ApiOperation("交换两个Integer的值")
    @GetMapping("/invoke/swap")
    public void invokeTest(@RequestParam("i1") Integer i1, @RequestParam("i2") Integer i2) {
        try {
            invokeService.swap(i1, i2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
