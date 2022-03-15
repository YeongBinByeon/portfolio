package com.example.mvc.controller;

import com.example.mvc.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {
    @PutMapping("/put")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto){
        System.out.println(putRequestDto.toString());
        return putRequestDto;

    }

    @PutMapping("/put2/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto, @PathVariable(name="userId") Long id){
        System.out.println(id);
        return putRequestDto;

    }
}
