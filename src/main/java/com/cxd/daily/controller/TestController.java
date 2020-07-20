package com.cxd.daily.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public int test(){
        ClassPathResource resource = new ClassPathResource("");
        try {
            System.out.println(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1 ;
    }
}
