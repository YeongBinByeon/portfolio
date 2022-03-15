package com.example.mvc.controller;

import com.example.mvc.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData){
        requestData.entrySet().forEach(stringObjectEntry -> {
            System.out.println("key : "+stringObjectEntry.getKey());
            System.out.println("value : "+stringObjectEntry.getValue());
        });
    }

    @PostMapping("/post2")
    public void post2(@RequestBody PostRequestDto requestData){
        System.out.println(requestData);
    }


}

