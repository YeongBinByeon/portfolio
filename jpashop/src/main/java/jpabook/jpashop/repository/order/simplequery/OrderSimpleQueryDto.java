package jpabook.jpashop.repository.order.simplequery;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long orderId, String name, LocalDateTime localDateTime, OrderStatus orderStatus, Address address){
        this.orderId = orderId;
        this.name = name; //LAZY 초기화 : 영속성 컨텍스트가 MemberID를 가지고 영속성 컨텍스트를 찾아온다.
        // 없으면 DB 쿼리 날린다.
        this.orderDate = localDateTime;
        this.orderStatus = orderStatus;
        this.address = address;
    }

    public OrderSimpleQueryDto(Order order){
        this.orderId = order.getId();
        this.name = order.getMember().getName(); //LAZY 초기화 : 영속성 컨텍스트가 MemberID를 가지고 영속성 컨텍스트를 찾아온다.
        // 없으면 DB 쿼리 날린다.
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
    }
}