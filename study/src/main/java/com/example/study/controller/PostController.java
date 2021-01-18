package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // 주로 아래 경우에 많이 사용된다.
    // HTML <Form>
    // ajax 검색
    // http 통신을 할 때  post의 body -> data
    // 어떤 방식으로(Content-Type) 받을 건지 우측의 종류가 있다. json,xml, multipart-fom / text-plain
    // 대략적인 이유는 파라메타를 많이 사용하기 때문이다.

    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put(){

    }

    @PatchMapping("/patchMethod")
    public void patch(){

    }
}
