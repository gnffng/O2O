package com.bong.o2o.dao.order;

import java.util.*;
import com.bong.o2o.dao.product.MainMenu;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "idJsonCount")
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orderSheet")
    OrderSheet orderSheet;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderMenu")
    List<OrderTopping> orderToppings;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mainMenu")
    MainMenu mainMenu;

    Long amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderSheet getOrderSheet() {
        return orderSheet;
    }

    public void setOrderSheet(OrderSheet orderSheet) {
        this.orderSheet = orderSheet;
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }

    public List<OrderTopping> getOrderToppings() {
        return orderToppings;
    }

    public void setOrderToppings(List<OrderTopping> orderToppings) {
        this.orderToppings = orderToppings;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
