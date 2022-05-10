package com.bong.o2o.dao.order;

import com.bong.o2o.dao.product.Topping;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJsonCount")
public class OrderTopping {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderMenu")
    OrderMenu orderMenu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topping")
    Topping topping;

    int count;
    Long amount;

    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderMenu getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
