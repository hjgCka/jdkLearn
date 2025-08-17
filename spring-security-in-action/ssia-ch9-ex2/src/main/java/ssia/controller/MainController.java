package ssia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description
 * @Author hjg
 * @Date 2025-08-17 22:57
 */
@Controller
public class MainController {

    @GetMapping("/main")
    public String main() {
        return "main.html";
    }
}
