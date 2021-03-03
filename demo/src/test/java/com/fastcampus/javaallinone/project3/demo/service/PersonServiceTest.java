package com.fastcampus.javaallinone.project3.demo.service;

import com.fastcampus.javaallinone.project3.demo.domain.Block;
import com.fastcampus.javaallinone.project3.demo.domain.Person;
import com.fastcampus.javaallinone.project3.demo.repository.BlockRepository;
import com.fastcampus.javaallinone.project3.demo.repository.PersonRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void getPeopleExcludeBlocks(){
        List<Person> result = personService.getPeopleExcludeBlocks();
        Assert.assertEquals(result.size(), 3);
        Assert.assertEquals(result.get(0).getName(), "martin");
        Assert.assertEquals(result.get(1).getName(), "david");
        Assert.assertEquals(result.get(2).getName(), "benny");
    }

    @Test
    void getPeopleByName(){

        List<Person> result = personService.getPeopleByName("martin");

        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0).getName(), "martin");
    }

}