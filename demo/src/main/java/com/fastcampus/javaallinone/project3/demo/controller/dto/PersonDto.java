package com.fastcampus.javaallinone.project3.demo.controller.dto;

import com.fastcampus.javaallinone.project3.demo.domain.dto.Birthday;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonDto {
    private String name;
    private int age;
    private String hobby;
    private String bloodType;
    private String address;
    private LocalDate birthday;
    private String job;
    private String phoneNumber;

}
