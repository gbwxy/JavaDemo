package com.gbwxy.demo.controller;

import com.gbwxy.demo.utils.Fibonacci;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "OOMContorller", tags = "OMM“Ï≥£")
@RestController
public class OOMContorller extends BaseController {

    @Resource
    Fibonacci fibonacci;

    @ApiOperation("Fibonacci")
    @GetMapping("/oom/test")
    public void oomFibonacci(@RequestParam("count") int count) {
        try {
            fibonacci.fibonacci(count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
