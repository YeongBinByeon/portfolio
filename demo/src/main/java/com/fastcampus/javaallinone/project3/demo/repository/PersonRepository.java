package com.fastcampus.javaallinone.project3.demo.repository;

import com.fastcampus.javaallinone.project3.demo.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);



    @Query(value = "select person from Person person where person.birthday.monthOfBirthday = :monthOfBirthday")
    //아래는 native query로 작성한 것
       // @Query(value = "select * from person where month_of_birthday = :monthOfBirthday", nativeQuery = true)
    List<Person> findByMonthOfBirthday(@Param("monthOfBirthday") int monthOfBirthday);

    @Query(value = "select * from person where person.deleted = true", nativeQuery = true)
    List<Person> findPeopleDeleted();

}
