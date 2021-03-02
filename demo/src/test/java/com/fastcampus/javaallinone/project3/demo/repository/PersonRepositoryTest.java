package com.fastcampus.javaallinone.project3.demo.repository;

import com.fastcampus.javaallinone.project3.demo.domain.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        person.setBloodType("A");

        personRepository.save(person);
        System.out.println(personRepository.findAll());

        List<Person> people = personRepository.findAll();
        Assert.assertEquals(people.size(),1);
        Assert.assertEquals(people.get(0).getName(), "martin");
        Assert.assertEquals(people.get(0).getAge(),10);
        Assert.assertEquals(people.get(0).getBloodType(), "A");
    }

    @Test
    void hashCodeAndEquals(){
        Person person1 = new Person("martin", 10, "A");
        Person person2 = new Person("martin", 10, "A");
        System.out.println(person1.equals(person2));
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());

        Map<Person, Integer> map = new HashMap<>();
        map.put(person1, person1.getAge());

        System.out.println(map);
        System.out.println(map.get(person2));
    }

    @Test
    void findByBloodType(){
        givenPerson("martin", 10, "A");
        givenPerson("david", 9, "B");
        givenPerson("dennis", 8, "O");
        givenPerson("sophia", 7, "AB");
        givenPerson("benny", 7, "A");
        givenPerson("john", 7, "A");

        List<Person> result = personRepository.findByBloodType("A");

        result.forEach(System.out::println);

    }

    @Test
    void findByBirthdayBetween(){
        givenPerson("martin", 10, "A", LocalDate.of(1991,8,15));
        givenPerson("david", 9, "B", LocalDate.of(1992,7,10));
        givenPerson("dennis", 8, "O", LocalDate.of(1993,1,5));
        givenPerson("sophia", 7, "AB", LocalDate.of(1994,6,30));
        givenPerson("benny", 7, "A", LocalDate.of(1995,8,30));

        List<Person> result = personRepository.findByBirthdayBetween(LocalDate.of(1991,8,1),LocalDate.of(1991,8,31));
        result.forEach(System.out::println);
    }
    private void givenPerson(String name, int age, String bloodType){
        givenPerson(name, age, bloodType, null);
    }

    private void givenPerson(String name, int age, String bloodType, LocalDate birthday){
        Person person = new Person(name, age, bloodType);
        person.setBirthday(birthday);
        personRepository.save(person);
    }

}