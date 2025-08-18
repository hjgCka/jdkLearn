package ssia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @Description
 *  @Author hjg
 *  @Date 2025-08-18 15:14
 */
@RestController
public class HelloController {

    @PostMapping("/hello")
    public String postHello() {
        return "Hello World!";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        return "Ciao!";
    }
}
