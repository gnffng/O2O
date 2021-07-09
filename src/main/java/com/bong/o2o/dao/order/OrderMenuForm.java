package com.bong.o2o.dao.order;

import java.util.*;

public class OrderMenuForm {
    Long idMainMenu;
    List<OrderToppingForm> orderToppingForms;

    public Long getIdMainMenu() {
        return idMainMenu;
    }

    public void setIdMainMenu(Long idMainMenu) {
        this.idMainMenu = idMainMenu;
    }

    public List<OrderToppingForm> getOrderToppingForms() {
        return orderToppingForms;
    }

    public void setOrderToppingForms(List<OrderToppingForm> orderToppingForms) {
        this.orderToppingForms = orderToppingForms;
    }
}
