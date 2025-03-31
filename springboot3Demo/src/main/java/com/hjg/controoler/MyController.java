package com.hjg.controoler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author hjg
 * @Date 2025-03-31 17:10
 */
@RestController
public class MyController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello Jack";
    }
}
