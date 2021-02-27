package com.fastcampus.javaallinone.project3.demo.repository;

import com.fastcampus.javaallinone.project3.demo.domain.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("martin");
        person.setAge(10);

        personRepository.save(person);
        //System.out.println(personRepository.findAll());
        //Assert.assertEquals();
        List<Person> people = personRepository.findAll();
        Assert.assertEquals(people.size(),1);
        Assert.assertEquals(people.get(0).getName(), "martin");
        Assert.assertEquals(people.get(0).getAge(),10);
    }

}