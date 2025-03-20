package com.leco.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefreshController {
    //@Autowired
    //private ContextRefresher contextRefresher;
    
    @GetMapping("/refresh")
    public String refresh() {
        //contextRefresher.refresh();
        return "Refreshed!";
    }
}
