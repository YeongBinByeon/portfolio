package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // ==table
//@Table(name = "user")  //Entity의 Class 이름과 DB의 Table 이름이 동일하다면 @Table Annotation 안써도 JPA가 알아서 mapping해준다.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account;
    // @Column(name = "account") // 변수의 명과 데이터베이스 column의 명이 동일하다면 이 annotation도 안써도 된다.
    private String email;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    // User class 입장에서
    // 1 : N
    // mappedBy = "user" 는 OrderDetail 클래스 안에 user 라는 변수에 매칭시키겠다는것
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OrderDetail> orderDetailList;
}
