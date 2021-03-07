package com.fastcampus.javaallinone.project3.demo.service;

import com.fastcampus.javaallinone.project3.demo.domain.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @Test
    void getPerson(){

        Person person = personService.getPerson(3L);

        Assert.assertEquals(person.getName(), "dennis");

    }

    @Test
    void getPeopleByName(){

        List<Person> result = personService.getPeopleByName("martin");

        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getName(), "martin");
    }

}