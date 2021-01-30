package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String content;

    // 1 : N
    // LAZY = 지연로딩, EAGER = 즉시로딩
    // EAGER 타입은 연관계가 설정된 모든 Table의 정보를 가져온다.
    // 따라서 1-1 관계이거나 연관 관계가 1건 정도로 적게 존재할 때만 추천하는 타입임.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
