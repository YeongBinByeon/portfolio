package com.fastcampus.javaallinone.project3.demo.controller;

import com.fastcampus.javaallinone.project3.demo.controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.demo.domain.Person;
import com.fastcampus.javaallinone.project3.demo.exception.PersonNotFoundException;
import com.fastcampus.javaallinone.project3.demo.exception.RenameIsNotPermittedException;
import com.fastcampus.javaallinone.project3.demo.exception.dto.ErrorResponse;
import com.fastcampus.javaallinone.project3.demo.repository.PersonRepository;
import com.fastcampus.javaallinone.project3.demo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/person")
@RestController
@Slf4j
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public Person getPerson(@PathVariable long id){
        return personService.getPerson(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postPerson(@RequestBody PersonDto personDto){
        personService.put(personDto);

    }

    @PutMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, @RequestBody PersonDto personDto){
        personService.modify(id, personDto);


    }

    @PatchMapping("/{id}")
    public void modifyPerson(@PathVariable Long id, String name){

        personService.modify(id, name);


    }

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable Long id){
        personService.delete(id);
    }


}
