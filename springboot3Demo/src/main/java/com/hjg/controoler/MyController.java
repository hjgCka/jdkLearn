package com.hjg.controoler;

import com.hjg.entity.Person;
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

    @RequestMapping("/hello")
    public String hello() {
        return "hello Jack";
    }

    @GetMapping("/findByName")
    public Person findByName(String name) {
        Person person = new Person();
        person.setName(name);
        person.setAge(22);
        return person;
    }
}
