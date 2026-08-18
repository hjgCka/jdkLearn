package com.hjg.controoler;

import com.hjg.entity.Person;
import com.hjg.foo.BarCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author hjg
 * @Date 2025-03-31 17:10
 */
@RestController
public class MyController {

    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @RequestMapping("/hello")
    public String hello() {
        System.out.println(MyController.class.getClassLoader().toString());
        return "hello Jack";
    }

    @GetMapping("/findByName")
    public Person findByName(String name) {
        logger.info("name:{}", name);
        Person person = new Person();
        person.setName(name);
        person.setAge(22);
        return person;
    }

    @GetMapping("/bar")
    public String callBar() {
        BarCaller.callBar();
        return "bar";
    }
}
