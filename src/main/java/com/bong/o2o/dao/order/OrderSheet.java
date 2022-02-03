package com.bong.o2o.dao.order;

import java.util.*;
import javax.persistence.*;

@Entity
public class OrderSheet {
    public enum Status{
        Preparing, Ready, Finish;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToMany(mappedBy = "orderSheet")
    List<OrderMenu> orderMenus;

    Long amount;
    Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderMenu> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(List<OrderMenu> orderMenus) {
        this.orderMenus = orderMenus;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
