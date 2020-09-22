package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public abstract class Item extends BaseEntity{
    //아이템만 단독으로 테이블에 저장할 일 없다고 판단해서 추상으로 만듬.

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;
    private String name;
    private  int price;
    private int stockQuntatity;

    @ManyToMany(mappedBy =  "items")
    private List<Category> categories = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuntatity() {
        return stockQuntatity;
    }

    public void setStockQuntatity(int stockQuntatity) {
        this.stockQuntatity = stockQuntatity;
    }

}

