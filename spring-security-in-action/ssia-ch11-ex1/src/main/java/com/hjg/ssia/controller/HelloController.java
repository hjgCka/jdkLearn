package com.hjg.ssia.controller;

import com.hjg.ssia.service.NameService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-13 16:13
 */
@RestController
public class HelloController {

    private final NameService nameService;

    public HelloController(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello, " + nameService.getName();
    }

    @GetMapping("/secret/names/{name}")
    public List<String> names(@PathVariable String name) {
        return nameService.getSecretNames(name);
    }
}
