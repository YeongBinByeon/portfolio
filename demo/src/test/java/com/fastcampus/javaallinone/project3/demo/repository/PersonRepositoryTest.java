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
    void findByName(){
        List<Person> people = personRepository.findByName("tony");
        Assert.assertEquals(people.size(),1);

        Person person = people.get(0);
        assertAll(
                ()->Assert.assertEquals(person.getName(),"tony"),
                ()->Assert.assertEquals(person.getHobby(),"reading"),
                ()->Assert.assertEquals(person.getAddress(),"seoul"),
                ()->Assert.assertEquals(person.getBirthday(), Birthday.of(LocalDate.of(1991,7,10))),
                ()->Assert.assertEquals(person.getJob(),"officer"),
                ()->Assert.assertEquals(person.getPhoneNumber(),"010-2222-5555"),
                ()->Assert.assertEquals(person.isDeleted(),false)
        );
    }

    @Test
    void findByNameIfDeleted(){
        List<Person> people = personRepository.findByName("andrew");
        Assert.assertEquals(people.size(),0);
    }

    @Test
    void findByMonthOfBirthday(){
        List<Person> people = personRepository.findByMonthOfBirthday(7);

        Assert.assertEquals(people.size(), 2);
        assertAll(
                ()->Assert.assertEquals(people.get(0).getName(),"david"),
                ()->Assert.assertEquals(people.get(1).getName(),"tony")
        );
    }

    @Test
    void findPeopleDeleted(){
        List<Person> people = personRepository.findPeopleDeleted();

        Assert.assertEquals(people.size(),1);
        Assert.assertEquals(people.get(0).getName(),"andrew");
    }

}