package org.apache.shiro.samples;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @Author hjg
 * @Date 2025-07-11 16:23
 */
@Controller
public class CustomLoginController {

    private static final Logger log = LoggerFactory.getLogger(CustomLoginController.class);

    @PostMapping("/customLogin")
    public String redirectToList(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 通过验证就登录到list页面

        try {
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject().login(usernamePasswordToken);
        } catch (Exception e) {
            log.error("登录时报错", e);
            return "forward:/error";
        }

        return "redirect:http://127.0.0.1:5500/bootstrap/list.html";
    }

    @GetMapping("/customHello")
    @ResponseBody
    public String hello() {
        return "world";
    }
}
