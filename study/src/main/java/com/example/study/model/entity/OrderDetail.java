package com.example.study.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // java는 카멜 케이스를 쓰지만, db는 snake case를 쓰기 때문에 자동적으로 order_detail 테이블에 연결되게 된다.
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;

    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

    private Long itemId;
    private Long orderGroupId;


}
