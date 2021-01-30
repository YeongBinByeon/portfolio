package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // java는 카멜 케이스를 쓰지만, db는 snake case를 쓰기 때문에 자동적으로 order_detail 테이블에 연결되게 된다.
@ToString(exclude = {"user", "item"})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderAt;

    // OrderDetail 클래스 입장에서 적어야 함.
    // N : 1
    @ManyToOne
    private User user; // user_id 알아서 찾아감

    // OrderDetail 클래스 입장에서 적어야 함.
    // N : 1
    @ManyToOne
    private Item item;
}
