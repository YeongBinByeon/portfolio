package com.fastcampus.javaallinone.project3.demo.service;

import com.fastcampus.javaallinone.project3.demo.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.demo.domain.Person;
import com.fastcampus.javaallinone.project3.demo.domain.dto.Birthday;
import com.fastcampus.javaallinone.project3.demo.exception.PersonNotFoundException;
import com.fastcampus.javaallinone.project3.demo.exception.RenameIsNotPermittedException;
import com.fastcampus.javaallinone.project3.demo.repository.PersonRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
    @InjectMocks
    private PersonService personService;
    @Mock
    private PersonRepository personRepository;

    @Test
    void getAll(){
        when(personRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Lists.newArrayList(new Person("martin"), new Person("dennis"), new Person("tony"))));
        Page<Person> result = personService.getAll(PageRequest.of(0,3));

        Assert.assertEquals(result.getNumberOfElements(), 3);
        Assert.assertEquals(result.getContent().get(0).getName(), "martin");
        Assert.assertEquals(result.getContent().get(1).getName(), "dennis");
        Assert.assertEquals(result.getContent().get(2).getName(), "tony");
    }

    @Test
    void getPeopleByName(){
        when(personRepository.findByName("martin"))
                .thenReturn(Lists.newArrayList(new Person("martin")));
        List<Person> result = personService.getPeopleByName("martin");

        Assert.assertEquals(result.size(),1);
        Assert.assertEquals(result.get(0).getName(),"martin");
    }

    @Test
    void getPerson(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        Person person = personService.getPerson(1L);
        Assert.assertEquals(person.getName(), "martin");
    }

    @Test
    void getPersonIfNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());
        Person person = personService.getPerson(1L);
        Assert.assertNull(person);
    }

    @Test
    void put(){
        personService.put(mockPersonDto());

        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeInserted()));
    }

    @Test
    void modifyIfPersonNotFound(){
        when(personRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PersonNotFoundException.class, ()->personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modifyIfNameIsDifferent(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("tony")));

        assertThrows(RenameIsNotPermittedException.class, ()->personService.modify(1L, mockPersonDto()));
    }

    @Test
    void modify(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        personService.modify(1L, mockPersonDto());

        //verify(personRepository, times(1)).save(any(Person.class));
        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeUpdated()));

    }

    @Test
    void modifyByNameIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, ()->personService.modify(1L, "daniel"));
    }

    @Test
    void modifyByName(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        personService.modify(1L, "daniel");

        verify(personRepository, times(1)).save(argThat(new IsNameWillBeUpdated()));
    }

    @Test
    void deleteIfPersonNotFound(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, ()->personService.delete(1L));
    }

    @Test
    void delete(){
        when(personRepository.findById(1L))
                .thenReturn(Optional.of(new Person("martin")));
        personService.delete(1L);
        verify(personRepository, times(1)).save(argThat(new IsPersonWillBeDeleted()));

    }


    private PersonDto mockPersonDto(){
        return PersonDto.of("martin", "programming", "판교", LocalDate.now(), "programmer", "010-1111-2222");
    }

    private static class IsPersonWillBeUpdated implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                    && equals(person.getHobby(),"programming")
                    && equals(person.getAddress(),"판교")
                    && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                    && equals(person.getJob(),"programmer")
                    && equals(person.getPhoneNumber(),"010-1111-2222");
        }

        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }

    private static class IsNameWillBeUpdated implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return person.getName().equals("daniel");
        }
    }

    private static class IsPersonWillBeDeleted implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return person.isDeleted();
        }
    }

    private static class IsPersonWillBeInserted implements ArgumentMatcher<Person>{

        @Override
        public boolean matches(Person person) {
            return equals(person.getName(),"martin")
                    && equals(person.getHobby(),"programming")
                    && equals(person.getAddress(),"판교")
                    && equals(person.getBirthday(),Birthday.of(LocalDate.now()))
                    && equals(person.getJob(),"programmer")
                    && equals(person.getPhoneNumber(),"010-1111-2222");
        }
        private boolean equals(Object actual, Object expected){
            return expected.equals(actual);
        }
    }
}