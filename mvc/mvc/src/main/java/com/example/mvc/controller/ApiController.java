package com.example.mvc.controller;

import com.example.mvc.dto.User;
import com.example.mvc.dto.UserReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // 해당 Class는 REST API 처리하는 Controller
@RequestMapping("/api2") // URI를 지정해주는 Annotation
public class ApiController {

    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    //JSON
    //Response 동작 순서 : req -> object mapper -> object -> method -> object -> objectmapper -> json -> response
    @PostMapping("/json")
    public User json(@RequestBody User user){
        return user;
    }

    // ResponseEntity
    @PutMapping("/put")
    public ResponseEntity<User> put(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

}
