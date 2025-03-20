package com.leco.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author greg
 * @version 2023/8/16
 **/
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Value("${hello_str}")
    private String helloStr;
    
    @GetMapping
    @ResponseBody
    public String hello() {
        return helloStr;
    }
}
