package com.example.mvc.controller;

import com.example.mvc.dto.UserReq;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {
    @GetMapping(path = "/hello") // http://localhost:8081/api/get/hello
    public String getHello(){
        return"get hello";
    }

    // 아래 로직대로 간단하게 동작하는 것이 위 @GetMapping
    @RequestMapping(path = "/hi", method = RequestMethod.GET)  //http://localhost:8081/api/get/hi
    public String hi(){
        return "hi";
    }

    // http://127.0.0.1:8081/api/get/path-variable/{name}
    @GetMapping("/path-variable/{name}")
    public String pathVariable(@PathVariable(name = "name") String pathName){
        System.out.println("PathVariable : "+pathName);
        return pathName;
    }

    // http://localhost:8081/api/get/query-param?user=steve&email=steve@gmail.com&age=30
    @GetMapping("/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam){
        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry->{
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey()+" = "+entry.getValue()+"\n");
        });
        return sb.toString();

    }

    @GetMapping("query-param02")
    public String queryParam02(
        @RequestParam String name,
        @RequestParam String email,
        @RequestParam int age
        )
        {
            System.out.println(name);
            System.out.println(email);
            System.out.println(age);
            return name+" " + email + " "+age;
        }

        //객체로 만들어서 쿼리 파라메터가 바로 매핑이 되도록 하는 형태를 가장 많이 사용함
        //http://localhost:8081/api/get/query-param03?name=steve&email=steve@gmail.com&age=30
    @GetMapping("query-param03")
    public String queryParam03(UserReq userReq)
    {
        System.out.println(userReq.getName());
        System.out.println(userReq.getEmail());
        System.out.println(userReq.getAge());
        return userReq.toString();
    }


}
