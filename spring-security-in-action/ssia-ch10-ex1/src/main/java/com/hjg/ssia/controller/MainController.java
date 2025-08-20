package com.hjg.ssia.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-20 22:38
 */
@Controller
public class MainController {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    /**
     * @CrossOrigin注解默认允许所有的域访问。
     * @return
     */
    @CrossOrigin
    @PostMapping("/test")
    @ResponseBody
    public String test() {
        logger.info("test method called");
        return "HELLO";
    }

    @PostMapping("/test2")
    @ResponseBody
    public String test2() {
        logger.info("test2 method called");
        return "HELLO WORLD";
    }
}
