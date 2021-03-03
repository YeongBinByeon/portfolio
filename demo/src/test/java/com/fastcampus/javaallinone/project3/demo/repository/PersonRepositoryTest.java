package com.fastcampus.javaallinone.project3.demo.repository;

import com.fastcampus.javaallinone.project3.demo.domain.Person;
import com.fastcampus.javaallinone.project3.demo.domain.dto.Birthday;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    void crud(){
        Person person = new Person();
        person.setName("john");
        person.setAge(10);
        person.setBloodType("A");

        personRepository.save(person);
        System.out.println(personRepository.findAll());

        List<Person> result = personRepository.findByName("john");
        Assert.assertEquals(result.size(),1);
        Assert.assertEquals(result.get(0).getName(), "john");
        Assert.assertEquals(result.get(0).getAge(),10);
        Assert.assertEquals(result.get(0).getBloodType(), "A");
    }

    @Test
    void findByBloodType(){

        List<Person> result = personRepository.findByBloodType("A");

        Assert.assertEquals(result.size(),2);
        Assert.assertEquals(result.get(0).getName(),"martin");
        Assert.assertEquals(result.get(1).getName(),"benny");


    }

    @Test
    void findByBirthdayBetween(){

        List<Person> result = personRepository.findByMonthOfBirthday(8);
        Assert.assertEquals(result.size(),2);
        Assert.assertEquals(result.get(0).getName(),"martin");
        Assert.assertEquals(result.get(1).getName(),"sophia");

    }



}