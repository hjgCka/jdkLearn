package com.hjg.service.impl;

import com.hjg.entity.Person;
import com.hjg.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author hjg
 * @Date 2025-05-29 16:44
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Person findById(Integer id) {
        Person person = new Person();
        person.setAge(20);
        person.setName("James");
        return person;
    }
}
