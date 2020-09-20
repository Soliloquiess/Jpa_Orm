package jpabook.jpashop.domain;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
//
//    @Column(name = "MEMBER_ID")
//    private Long memberId;
// 이전에는 멤버 외래키값을 매핑해서 그대로 가지고 있었는데 필요가 없어짐.


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private  Member member;

    @OneToMany(mappedBy = "order") //이 연관관계의 주인은 orderitem에 있는 오더 아이디가 연관관계 주인
    private List<OrderItem> orderItems =new ArrayList<>();
    private LocalDateTime orderDate;



    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);

    }
}
