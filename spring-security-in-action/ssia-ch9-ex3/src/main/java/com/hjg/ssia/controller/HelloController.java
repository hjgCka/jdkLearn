package com.hjg.ssia.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-13 16:13
 */
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @PostMapping("/ciao")
    public String postCiao() {
        return "Post ciao!";
    }

    @GetMapping("/hello")
    public String getHello(HttpServletRequest request){
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        logger.info("CSRF token: "  + token.getToken());
        return "Get hello";
    }
}
